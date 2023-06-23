package com.stackroute.customerservice.controller;

import com.stackroute.customerservice.dto.BookingDetailsResponse;
import com.stackroute.customerservice.dto.CancelBookingResponse;
import com.stackroute.customerservice.dto.AddBookingDetails;
import com.stackroute.customerservice.dto.UpdateBookingDetails;
import com.stackroute.customerservice.dto.CancelBooking;
import com.stackroute.customerservice.service.IBookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@CrossOrigin("*")
@RequestMapping("/api/v1/bookings")
@RestController
public class BookingDetailsController {


    private IBookingDetailsService iBookingDetailsService;
    @Autowired
    public BookingDetailsController(IBookingDetailsService iBookingDetailsService)
    {
        this.iBookingDetailsService=iBookingDetailsService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/saveBooking")
    public BookingDetailsResponse saveBookingDetails(@RequestBody  @Valid AddBookingDetails addBookingDetails)
    {
          BookingDetailsResponse bookingDetailsResponse=iBookingDetailsService.saveBooking(addBookingDetails);
          return  bookingDetailsResponse;
    }


    @GetMapping("/byBookingId/{bookingId}")
    public BookingDetailsResponse getBookingById(@PathVariable String bookingId)
    {
        BookingDetailsResponse bookingDetailsResponse=iBookingDetailsService.getBookingById(bookingId);
        return  bookingDetailsResponse;
    }

    @GetMapping("/allBookingsByCustomer/{customerEmailId}")
    public List<BookingDetailsResponse> getBookingByCustomerEmailId(@PathVariable String customerEmailId)
    {
        List<BookingDetailsResponse> bookingDetailsResponse=iBookingDetailsService.getAllBookingByCustomerEmailId(customerEmailId);
        return  bookingDetailsResponse;
    }

    @GetMapping("/allBookingsByExpert/{expertEmailId}")
    public List<BookingDetailsResponse> getBookingByExpertEmailId(@PathVariable String expertEmailId)
    {
        List<BookingDetailsResponse>  bookingDetailsResponse=iBookingDetailsService.getAllBookingByExpertEmailId(expertEmailId);
        return  bookingDetailsResponse;
    }

    @GetMapping("/allBookingsByStatus/{status}")
    public  List<BookingDetailsResponse> getBookingByStatus(@PathVariable String status)
    {
        List<BookingDetailsResponse>  bookingDetailsResponse=iBookingDetailsService.getAllBookingByStatus(status);
        return  bookingDetailsResponse;
    }

    @GetMapping("/allBookings/{expertEmailId}/{date}")
    public  List<BookingDetailsResponse> getBookingByExpertEmailId(@PathVariable String expertEmailId,String date)
    {
        List<BookingDetailsResponse>  bookingDetailsResponse=iBookingDetailsService.getAllBookingByExpertIdAndDate(expertEmailId,date);
        return  bookingDetailsResponse;
    }

    @PutMapping("/rescheduleBooking")
    public  BookingDetailsResponse updateBooking(@RequestHeader HttpHeaders headers,@RequestBody @Valid UpdateBookingDetails updateBookingDetails)
    {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        BookingDetailsResponse bookingDetailsResponse=iBookingDetailsService.updateBooking(updateBookingDetails,token.replace("Bearer ", ""));
        return  bookingDetailsResponse;
    }

    @PutMapping("/cancelBooking")
    public  CancelBookingResponse updateBooking(@RequestHeader HttpHeaders headers,@RequestBody @Valid CancelBooking cancelBooking)
    {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        CancelBookingResponse cancelBookingResponse=iBookingDetailsService.cancelBooking(cancelBooking,token.replace("Bearer ", ""));
        return  cancelBookingResponse;
    }

}
