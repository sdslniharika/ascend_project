package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.entity.LoginDetails;
import com.stackroute.authenticationservice.exceptions.InvalidUsernameException;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String, String> generateToken(LoginDetails details) throws InvalidUsernameException;
}
