package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.entity.LoginDetails;
import com.stackroute.authenticationservice.exceptions.InvalidUsernameException;

public interface AuthenticationService {

    String login(LoginDetails details) throws InvalidUsernameException;

    void save(LoginDetails details);

    String deleteByEmail(String emailId) throws InvalidUsernameException;
}
