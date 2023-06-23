package com.gl.resortManagement.validations;

import com.gl.resortManagement.dto.AddGuest;
import com.gl.resortManagement.exceptions.InvalidInputException;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    public void validateAddGuest(AddGuest addGuest){
        if(addGuest.getName().isBlank() || addGuest.getName().length()>25){
            throw new InvalidInputException("Name cannot be Blank or Greater than 25 chracters");
        }
    }

    public void validateId(Integer id){
        if(id<1){
            throw new InvalidInputException("ID cannot be less than one");
        }
    }
}
