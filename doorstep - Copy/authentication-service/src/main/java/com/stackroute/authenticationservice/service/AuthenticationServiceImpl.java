package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.entity.LoginDetails;
import com.stackroute.authenticationservice.exceptions.InvalidUsernameException;
import com.stackroute.authenticationservice.repository.AuthenticationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    AuthenticationDao dao;

    SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationDao authenticationDao, SecurityTokenGenerator securityTokenGenerator) {
        this.dao = authenticationDao;
        this.securityTokenGenerator = securityTokenGenerator;

    }

    public void save(LoginDetails details) {
        dao.save(details);
    }

    @Override
    public String deleteByEmail(String emailId) throws InvalidUsernameException {
        Optional<LoginDetails> user = dao.findById(emailId);
        if (user.isEmpty()) {
            throw new InvalidUsernameException("User not found");
        }
        dao.deleteById(emailId);
        return emailId;
    }

    @Override
    public String login(LoginDetails details) throws InvalidUsernameException {
        Optional<LoginDetails> user = dao.findById(details.getEmailId());
        if (user.isEmpty()) {
            throw new InvalidUsernameException("User not found");
        }
        LoginDetails loginDetails = user.get();

        if (loginDetails.getEmailId().equals(details.getEmailId()) && loginDetails.getPassword().equals(details.getPassword())) {
            Map<String, String> tokenMap = securityTokenGenerator.generateToken(loginDetails);
            return "Token = " + tokenMap.get("token");
        }
        throw new InvalidUsernameException("Invalid Password for EmailId");


    }
}
