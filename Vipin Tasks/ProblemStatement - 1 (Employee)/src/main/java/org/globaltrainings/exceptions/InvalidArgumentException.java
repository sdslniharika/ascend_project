package org.globaltrainings.exceptions;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String msg){
        super(msg);
    }
}
