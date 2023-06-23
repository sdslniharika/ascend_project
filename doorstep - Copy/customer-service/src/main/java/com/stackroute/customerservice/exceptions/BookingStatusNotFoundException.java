package com.stackroute.customerservice.exceptions;

public class BookingStatusNotFoundException extends RuntimeException {
    public BookingStatusNotFoundException(String message) {
        super(message);
    }
}
