package org.globaltrainings.exceptions;

public class EmployeeNotFoundException extends RuntimeException {


    public EmployeeNotFoundException(String msg) {
        super(msg);
    }


}