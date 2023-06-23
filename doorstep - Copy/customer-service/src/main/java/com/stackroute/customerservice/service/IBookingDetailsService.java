package com.stackroute.customerservice.service;


import com.stackroute.customerservice.dto.BookingDetailsResponse;
import com.stackroute.customerservice.dto.CancelBookingResponse;
import com.stackroute.customerservice.dto.AddBookingDetails;
import com.stackroute.customerservice.dto.UpdateBookingDetails;
import com.stackroute.customerservice.dto.CancelBooking;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface IBookingDetailsService {

    BookingDetailsResponse saveBooking(@Valid AddBookingDetails bookingDetails);

    BookingDetailsResponse getBookingById(String bookingId);

    List<BookingDetailsResponse> getAllBookingByCustomerEmailId(String emailId);

    List<BookingDetailsResponse> getAllBookingByExpertEmailId(String emailId);

    List<BookingDetailsResponse> getAllBookingByExpertIdAndDate(String emailId, String date);

    List<BookingDetailsResponse> getAllBookingByStatus(String status);

    BookingDetailsResponse updateBooking(@Valid UpdateBookingDetails bookingDetails,String token);

    CancelBookingResponse cancelBooking(@Valid CancelBooking cancelBooking,String token);


}

