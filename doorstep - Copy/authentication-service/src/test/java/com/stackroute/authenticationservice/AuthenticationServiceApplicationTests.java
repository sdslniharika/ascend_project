package com.stackroute.authenticationservice;

import com.stackroute.authenticationservice.entity.LoginDetails;
import com.stackroute.authenticationservice.exceptions.InvalidUsernameException;
import com.stackroute.authenticationservice.repository.AuthenticationDao;
import com.stackroute.authenticationservice.service.AuthenticationService;
import com.stackroute.authenticationservice.service.AuthenticationServiceImpl;
import com.stackroute.authenticationservice.service.JWTSecurityTokenGenerator;
import com.stackroute.authenticationservice.service.SecurityTokenGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class AuthenticationServiceApplicationTests {
    @Autowired
    AuthenticationService authenticationService;
    @Mock
    SecurityTokenGenerator securityTokenGenerator;
    @Mock
    AuthenticationDao authenticationDao;


    @BeforeEach
    public void before() {
        securityTokenGenerator = new JWTSecurityTokenGenerator(authenticationDao);
        authenticationService = new AuthenticationServiceImpl(authenticationDao, securityTokenGenerator);

    }

    @Test
    void testLoginService_SaveDetailsSucessfully() throws InvalidUsernameException {
        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setEmailId("venkatmahesh@gmail.com");
        loginDetails.setPassword("venkat44");
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("userRole", loginDetails.toString());
        tokenMap.put("token", "eybghsjowbsnshs");
       // Mockito.when(authenticationDao.save(Mockito.any(LoginDetails.class))).thenReturn(loginDetails);
        Mockito.when(authenticationDao.findById(Mockito.any(String.class))).thenReturn(Optional.of(loginDetails));
        String token = authenticationService.login(loginDetails);
        Assertions.assertTrue(StringUtils.isNotBlank(token));


    }

}
