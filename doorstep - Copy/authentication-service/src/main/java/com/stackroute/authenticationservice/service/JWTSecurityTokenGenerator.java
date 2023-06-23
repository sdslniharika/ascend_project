package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.entity.LoginDetails;
import com.stackroute.authenticationservice.exceptions.InvalidUsernameException;
import com.stackroute.authenticationservice.repository.AuthenticationDao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class JWTSecurityTokenGenerator implements SecurityTokenGenerator {
    private AuthenticationDao authenticationDao;

    @Autowired
    public JWTSecurityTokenGenerator(AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }


    @Override
    public Map<String, String> generateToken(LoginDetails loginDetails) throws InvalidUsernameException {
        String jwtToken = "";
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", loginDetails.getEmailId());
        claims.put("role", loginDetails.getRole());
        jwtToken = Jwts.builder().setClaims(claims).setSubject(loginDetails.getEmailId()).setIssuedAt(new Date()).setExpiration(Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", jwtToken);
        try {
            Optional<LoginDetails> userExists = authenticationDao.findById(loginDetails.getEmailId());

            if (userExists.isEmpty()) {
                throw new InvalidUsernameException("Invalid EmailId");
            } else {
                tokenMap.put("userRole", userExists.get().toString());
                return tokenMap;
            }

        } catch (Exception e) {
            throw new InvalidUsernameException(e.getMessage());
        }

    }

}
