package com.gl.resortManagement.exceptions;

public class GuestNotFoundException extends RuntimeException{
    public GuestNotFoundException(String message){
        super(message);
    }
}
