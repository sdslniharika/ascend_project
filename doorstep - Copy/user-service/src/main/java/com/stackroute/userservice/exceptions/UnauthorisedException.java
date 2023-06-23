package com.stackroute.userservice.exceptions;

public class UnauthorisedException extends Exception {
    public UnauthorisedException() {
    }

    public UnauthorisedException(String message) {
        super(message);
    }
}
