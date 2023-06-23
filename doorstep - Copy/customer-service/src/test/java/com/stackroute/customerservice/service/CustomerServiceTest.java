package com.stackroute.customerservice.service;

import com.stackroute.customerservice.config.Producer;
import com.stackroute.customerservice.constants.BookingStatus;
import com.stackroute.customerservice.constants.SlotStatus;
import com.stackroute.customerservice.documents.BookingDetails;
import com.stackroute.customerservice.dto.*;
import com.stackroute.customerservice.repository.BookingDetailsRepository;
import com.stackroute.customerservice.utility.BookingDetailsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    BookingDetailsServiceImp bookingDetailsServiceImp;
    @Mock
    SlotService slotService;
    @Mock
    BookingDetailsRepository repository;
    @Mock
    Producer producer;
    @Spy
    BookingDetailsUtil util;

    private List<BookingDetails> bookingDetailsList;

    private BookingDetails bookingDetails;

    private  BookingDetailsResponse bookingDetailsResponse;

    private Slot slot;
    @BeforeEach
    void setUp(){
        bookingDetailsList =new ArrayList<>();
        bookingDetails = new BookingDetails();
        bookingDetailsResponse=new BookingDetailsResponse();
        slot=new Slot();

        LocalTime strtime = LocalTime.of(7, 00, 45);
        LocalTime endtime = LocalTime.of(7, 45);

        LocalDate bookedDate = LocalDate.now();
        LocalTime bookedTime = LocalTime.now();

        slot.setSlotId("slot1");
        slot.setSlotStatus(SlotStatus.AVAILABLE);
      //  slot.setSlotDate(bookedDate);
     /*   slot.setSlotStartTime(strtime);
        slot.setSlotStartTime(endtime);*/


        bookingDetails.setCustomerEmailId("customer@email.com");
        bookingDetails.setExpertEmailId("expert@email.com");
        bookingDetails.setServiceName("service1");
        bookingDetails.setPrice(300);
        bookingDetails.setBookingStatus(BookingStatus.CONFIRMED);
        bookingDetails.setSlot(slot);
        bookingDetails.setBookingTime(bookedTime);
        bookingDetails.setBookingDate(bookedDate);
        bookingDetails.setBookingId("booking1");

        bookingDetailsList.add(bookingDetails);
    }

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveBookingTest() throws Exception{
        AddBookingDetails addBookingDetails=new AddBookingDetails();

        addBookingDetails.setCustomerEmailId("customer@email.com");
        addBookingDetails.setExpertEmailId("expert@email.com");
        addBookingDetails.setServiceName("service1");
        addBookingDetails.setPrice(300);
        addBookingDetails.setSlotId("slot1");
        addBookingDetails.setBookingStatus("confirmed");
        LocalDate bookedDate = LocalDate.now();

        LocalTime strtime = LocalTime.of(7, 00, 45);
        LocalTime endtime = LocalTime.of(7, 45);
        slot.setSlotId("slot1");
        slot.setSlotStatus(SlotStatus.AVAILABLE);
     //   slot.setSlotDate(bookedDate);
      /*  slot.setSlotStartTime(strtime);
        slot.setSlotStartTime(endtime);*/

        when(repository.save(Mockito.any())).thenReturn(bookingDetails);

        BookingDetailsResponse booking= bookingDetailsServiceImp.saveBooking(addBookingDetails);

        assertEquals(addBookingDetails.getCustomerEmailId(), booking.getCustomerEmailId());
        assertFalse(booking.toString().isBlank());
    }

    @Test
    void getBookingByIdTest() throws Exception{
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(bookingDetails));
        BookingDetailsResponse bookingDetailsResponse=util.toBookingDetailsResponse(bookingDetails);
        assertEquals(bookingDetailsResponse.getClass(), bookingDetailsServiceImp.getBookingById("booking1").getClass());
    }

    @Test
    void getAllBookingByCustomerEmailIdTest() throws Exception{

        when(repository.findByCustomerEmailId(Mockito.any())).thenReturn(bookingDetailsList);
        List<BookingDetailsResponse> bookingDetails=bookingDetailsServiceImp.getAllBookingByCustomerEmailId("customer@email.com");
        assertEquals(1, bookingDetails.size());
    }

    @Test
    void getAllBookingByExpertEmailIdTest() throws Exception{

        when(repository.findByExpertEmailId(Mockito.any())).thenReturn(bookingDetailsList);
        List<BookingDetailsResponse> bookingDetails=bookingDetailsServiceImp.getAllBookingByExpertEmailId("expert@email.com");
        assertEquals(1, bookingDetails.size());
    }

    @Test
    void getAllBookingByExpertIdAndDateTest() throws Exception{
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String  bookedDate = LocalDate.now().format(dtf).toString();

        when(repository.findByExpertEmailIdAndBookingDate(Mockito.any(),Mockito.any())).thenReturn(bookingDetailsList);
        List<BookingDetailsResponse> bookingDetails=bookingDetailsServiceImp.getAllBookingByExpertIdAndDate("expert@email.com",bookedDate);
        assertEquals(1, bookingDetails.size());
    }

    @Test
    void getAllBookingByStatusTest() throws Exception{

        when(repository.findByBookingStatus(Mockito.any())).thenReturn(bookingDetailsList);
        List<BookingDetailsResponse> bookingDetails=bookingDetailsServiceImp.getAllBookingByStatus("CONFIRMED");
        assertEquals(1, bookingDetails.size());
    }

    @Test
    void updateBookingTest() throws Exception {
        UpdateBookingDetails updateBookingDetails = new UpdateBookingDetails();
        updateBookingDetails.setBookingId("booking1");
        updateBookingDetails.setSlotId("slot1");
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjcxNzg1NzU4LCJleHAiOjE2NzE3ODc1NTh9.zUJqCZ2T1SKZVUeBRZjEQtOFFFoP3v-y-Ac1InJ-PVM";
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(bookingDetails));
        assertThrows(Exception.class,() -> {bookingDetailsResponse = bookingDetailsServiceImp.updateBooking(updateBookingDetails,token);});
    }

    @Test
    void cancelBookingTest() throws Exception {
        CancelBooking cancelBooking= new CancelBooking();
        cancelBooking.setBookingId("booking1");
        cancelBooking.setBookingStatus("CANCELED");
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjcxNzg1NzU4LCJleHAiOjE2NzE3ODc1NTh9.zUJqCZ2T1SKZVUeBRZjEQtOFFFoP3v-y-Ac1InJ-PVM";
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(bookingDetails));
        assertThrows(Exception.class,() -> {
            CancelBookingResponse cancelBookingResponse = bookingDetailsServiceImp.cancelBooking(cancelBooking, token);
        });
    }

}
