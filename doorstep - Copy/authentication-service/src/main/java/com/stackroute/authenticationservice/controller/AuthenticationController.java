package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.entity.LoginDetails;
import com.stackroute.authenticationservice.exceptions.InvalidUsernameException;
import com.stackroute.authenticationservice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CrossOrigin("*")

@RequestMapping("/api/v1/authentication")
public class AuthenticationController {
    @Autowired
    AuthenticationService service;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody LoginDetails loginDetails) throws InvalidUsernameException {
        String response = service.login(loginDetails);
        if (response != null) {
            return response;
        } else {
            return "Login Fail";
        }


    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value = "/deleteByEmail/{emailId}")
    public String deleteByEmail(@PathVariable String emailId) throws InvalidUsernameException {
        return service.deleteByEmail(emailId);
    }


}
