package com.stackroute.customerservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({BookingNotFoundException.class,
            BookingStatusNotFoundException.class,UnauthorisedException.class})
    public String toHandleBookingNotFoundException(Exception e) {
        return e.getLocalizedMessage();
    }

}
