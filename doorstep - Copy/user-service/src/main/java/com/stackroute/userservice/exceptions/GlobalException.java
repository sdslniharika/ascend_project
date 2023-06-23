package com.stackroute.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ProfileAlreadyExist.class)
    public ResponseEntity<Object> exception(ProfileAlreadyExist ex) {
        return new ResponseEntity<>("EmailId Already Exists", HttpStatus.FOUND);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Object> exception(ResourceNotFound exe) {
        return new ResponseEntity<>("EmailId does not exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdNotFound.class)
    public ResponseEntity<Object> exception(IdNotFound exe) {
        return new ResponseEntity<>("Data not found for this Id", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorisedException.class)
    public ResponseEntity<Object> exception(UnauthorisedException exe) {
        return new ResponseEntity<>("Unauthorised User", HttpStatus.NOT_FOUND);
    }
}
