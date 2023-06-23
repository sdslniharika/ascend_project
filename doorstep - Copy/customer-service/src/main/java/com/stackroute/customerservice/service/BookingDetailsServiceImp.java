package com.stackroute.customerservice.service;

import com.stackroute.customerservice.config.Producer;
import com.stackroute.customerservice.constants.BookingStatus;
import com.stackroute.customerservice.constants.SlotStatus;
import com.stackroute.customerservice.documents.BookingDetails;
import com.stackroute.customerservice.dto.BookingDetailsResponse;
import com.stackroute.customerservice.dto.CancelBookingResponse;
import com.stackroute.customerservice.dto.AddBookingDetails;
import com.stackroute.customerservice.dto.UpdateBookingDetails;
import com.stackroute.customerservice.dto.CancelBooking;
import com.stackroute.customerservice.dto.UpdateSlotStatus;
import com.stackroute.customerservice.dto.Slot;

import com.stackroute.customerservice.exceptions.BookingNotFoundException;
import com.stackroute.customerservice.exceptions.UnauthorisedException;
import com.stackroute.customerservice.repository.BookingDetailsRepository;
import com.stackroute.customerservice.utility.BookingDetailsUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class BookingDetailsServiceImp implements IBookingDetailsService {
    private BookingDetailsRepository bookingDetailsRepo;
    private BookingDetailsUtil bookingDetailsUtil;
    private  SlotService slotService;
    private Producer producer;

    @Autowired
    public BookingDetailsServiceImp(BookingDetailsRepository bookingDetailsRepo,BookingDetailsUtil bookingDetailsUtil, SlotService slotService,Producer producer) {
        this.bookingDetailsRepo = bookingDetailsRepo;
        this.bookingDetailsUtil = bookingDetailsUtil;
        this.slotService=slotService;
        this.producer=producer;
    }

    @Override
    public BookingDetailsResponse saveBooking(AddBookingDetails addBookingDetails) {

        BookingDetails booking = new BookingDetails();
        booking.setCustomerEmailId(addBookingDetails.getCustomerEmailId());
        booking.setExpertEmailId(addBookingDetails.getExpertEmailId());
        booking.setServiceName(addBookingDetails.getServiceName());
        booking.setPrice(addBookingDetails.getPrice());

        LocalDate bookedDate = LocalDate.now();
        LocalTime bookedTime = LocalTime.now();

        booking.setBookingDate(bookedDate);
        booking.setBookingTime(bookedTime);

        UpdateSlotStatus updateSlotStatus=new UpdateSlotStatus();
       updateSlotStatus.setSlotId(addBookingDetails.getSlotId());
       updateSlotStatus.setSlotStatus(SlotStatus.NOT_AVAILABLE.toString());

        Slot slot=slotService.updateSlotStatus(updateSlotStatus,Optional.empty());

        booking.setSlot(slot);

        BookingStatus bookingStatus = bookingDetailsUtil.bookingStatusToEnum(addBookingDetails.getBookingStatus());
        booking.setBookingStatus(bookingStatus);

        booking = bookingDetailsRepo.save(booking);

        BookingDetailsResponse bookingDetailsResponse = bookingDetailsUtil.toBookingDetailsResponse(booking);

        producer.sendMessagetoRabbitMq(bookingDetailsResponse);
        return bookingDetailsResponse;

    }

    @Override
    public BookingDetailsResponse getBookingById(String bookingId) {

        BookingDetails bookingDetails= findById(bookingId);
        BookingDetailsResponse bookingDetailsResponse = bookingDetailsUtil.toBookingDetailsResponse(bookingDetails);
        return bookingDetailsResponse;
    }

    @Override
    public List<BookingDetailsResponse> getAllBookingByCustomerEmailId(String customerEmailId) {
        List<BookingDetails> bookingDetailsList=bookingDetailsRepo.findByCustomerEmailId(customerEmailId);

        if(bookingDetailsList.isEmpty())
        {
            throw new BookingNotFoundException("Bookings not found with given customer emailId = "+customerEmailId);
        }

        List<BookingDetailsResponse> bookingDetailsResponseList=bookingDetailsUtil.toBookingDetailsResponseList(bookingDetailsList);

        return  bookingDetailsResponseList;

    }

    @Override
    public List<BookingDetailsResponse> getAllBookingByExpertEmailId(String expertEmailId) {

        List<BookingDetails> bookingDetailsList=bookingDetailsRepo.findByExpertEmailId(expertEmailId);

        if(bookingDetailsList.isEmpty())
        {
            throw new BookingNotFoundException("Bookings not found with given customer emailId = "+expertEmailId);
        }

        List<BookingDetailsResponse> bookingDetailsResponseList=bookingDetailsUtil.toBookingDetailsResponseList(bookingDetailsList);

        return  bookingDetailsResponseList;
    }

    @Override
    public List<BookingDetailsResponse> getAllBookingByExpertIdAndDate(String emailId, String date) {

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate=LocalDate.parse(date,dtf);

        List<BookingDetails> bookingDetailsList=bookingDetailsRepo.findByExpertEmailIdAndBookingDate(emailId,localDate);

        List<BookingDetailsResponse> bookingDetailsResponseList=bookingDetailsUtil.toBookingDetailsResponseList(bookingDetailsList);

        return  bookingDetailsResponseList;

    }

    @Override
    public List<BookingDetailsResponse> getAllBookingByStatus(String status) {

        BookingStatus bookingStatus=bookingDetailsUtil.bookingStatusToEnum(status);
        List<BookingDetails> bookingDetailsList=bookingDetailsRepo.findByBookingStatus(bookingStatus);

        if(bookingDetailsList.isEmpty())
        {
            throw new BookingNotFoundException("Bookings not found for the given status = "+status);
        }
        List<BookingDetailsResponse> bookingDetailsResponseList=bookingDetailsUtil.toBookingDetailsResponseList(bookingDetailsList);
        return  bookingDetailsResponseList;
    }

    @Override
    public BookingDetailsResponse updateBooking(UpdateBookingDetails updateBookingDetails,String token) {

        BookingDetails bookingDetails = findById(updateBookingDetails.getBookingId());

        Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
        String emailId = (String) claims.get("sub");
        if(!emailId.equals(bookingDetails.getCustomerEmailId())){
            throw new UnauthorisedException("Unauthorised User");
        }

        UpdateSlotStatus updateSlotStatus=new UpdateSlotStatus();
        updateSlotStatus.setSlotId(updateBookingDetails.getSlotId());
        updateSlotStatus.setSlotStatus(SlotStatus.NOT_AVAILABLE.toString());

        Slot slot=slotService.updateSlotStatus(updateSlotStatus,Optional.of(bookingDetails.getSlot().getSlotId()));
        bookingDetails.setSlot(slot);
        bookingDetails.setBookingStatus(BookingStatus.RESCHEDULED);
        bookingDetails=bookingDetailsRepo.save(bookingDetails);

        BookingDetailsResponse bookingDetailsResponse = bookingDetailsUtil.toBookingDetailsResponse(bookingDetails);
        producer.sendMessagetoRabbitMq(bookingDetailsResponse);
        return bookingDetailsResponse;
    }

    @Override
    public CancelBookingResponse cancelBooking(CancelBooking cancelBooking,String token) {

        BookingDetails bookingDetails = findById(cancelBooking.getBookingId());

        Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
        String emailId = (String) claims.get("sub");
        if(!emailId.equals(bookingDetails.getCustomerEmailId())){
            throw new UnauthorisedException("Unauthorised User");
        }

        BookingStatus bookingStatus = bookingDetailsUtil.bookingStatusToEnum(cancelBooking.getBookingStatus());

        bookingDetails.setBookingStatus(bookingStatus);
        UpdateSlotStatus updateSlotStatus=new UpdateSlotStatus();

        updateSlotStatus.setSlotId(bookingDetails.getSlot().getSlotId());
        updateSlotStatus.setSlotStatus(SlotStatus.AVAILABLE.toString());

        Slot updateSlot=slotService.updateSlotStatus(updateSlotStatus,Optional.empty());

        bookingDetailsRepo.save(bookingDetails);

        CancelBookingResponse cancelBookingResponse=new CancelBookingResponse();
        cancelBookingResponse.setMessage("Canceled Successfully");
        cancelBookingResponse.setSlot(updateSlot);

        BookingDetailsResponse bookingDetailsResponse = bookingDetailsUtil.toBookingDetailsResponse(bookingDetails);
        producer.sendMessagetoRabbitMq(bookingDetailsResponse);
        return cancelBookingResponse;

    }

    private BookingDetails findById(String bookingId)
    {
        Optional<BookingDetails> optionalBookingDetails = bookingDetailsRepo.findById(bookingId);

        if (optionalBookingDetails.isEmpty()) {
            throw new BookingNotFoundException("Booking not found with the given id = " + bookingId);
        }
        return optionalBookingDetails.get();

    }

}
