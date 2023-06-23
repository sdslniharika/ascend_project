package com.stackroute.customerservice.utility;

import com.stackroute.customerservice.constants.BookingStatus;
import com.stackroute.customerservice.documents.BookingDetails;
import com.stackroute.customerservice.dto.BookingDetailsResponse;
import com.stackroute.customerservice.exceptions.BookingStatusNotFoundException;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingDetailsUtil {

    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
    public BookingDetailsResponse toBookingDetailsResponse(BookingDetails bookingDetails) {
        BookingDetailsResponse bookingDetailsResponse = new BookingDetailsResponse();

        bookingDetailsResponse.setBookingId(bookingDetails.getBookingId());


        bookingDetailsResponse.setBookingDate(dateFormatter.format(bookingDetails.getBookingDate()));
        bookingDetailsResponse.setBookingTime(timeFormatter.format(bookingDetails.getBookingTime()));

        bookingDetailsResponse.setPrice(bookingDetails.getPrice());
        bookingDetailsResponse.setServiceName(bookingDetails.getServiceName());
        bookingDetailsResponse.setExpertEmailId(bookingDetails.getExpertEmailId());
        bookingDetailsResponse.setCustomerEmailId(bookingDetails.getCustomerEmailId());
        bookingDetailsResponse.setSlot(bookingDetails.getSlot());
        bookingDetailsResponse.setBookingStatus(bookingDetails.getBookingStatus().toString());

        return bookingDetailsResponse;
    }

    public List<BookingDetailsResponse> toBookingDetailsResponseList(List<BookingDetails> bookingDetails) {
        return bookingDetails.stream().map(this::toBookingDetailsResponse).collect(Collectors.toList());

    }

    public BookingStatus bookingStatusToEnum(String stringBookingStatus) {
        BookingStatus[] bookingValues = BookingStatus.values();
        for (BookingStatus bookingStatus : bookingValues) {
            if (bookingStatus.toString().equalsIgnoreCase(stringBookingStatus)) {
                return bookingStatus;
            }
        }
        throw new BookingStatusNotFoundException("Status not found");
    }


}
