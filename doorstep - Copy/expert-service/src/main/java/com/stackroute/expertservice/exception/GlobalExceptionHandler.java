package com.stackroute.expertservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            InvalidArgumentException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            DateTimeParseException.class,
            SlotNotFoundException.class,
            ExpertAvailabilityNotFoundException.class,
    })
    public String handleInvalidArgument(Exception e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            InvalidTokenException.class
    })
    public String handleInvalidToken(Exception e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public String handleOtherException(Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }

}
