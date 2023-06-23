package com.stackroute.authenticationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.authenticationservice.entity.LoginDetails;
import com.stackroute.authenticationservice.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private AuthenticationService service;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testLogin_ShouldSaveDetailsSucessfully() throws Exception {
        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setEmailId("venkatmahesh@gmail.com");
        loginDetails.setPassword("vcnshs123");
        Mockito.when(service.login(Mockito.any(LoginDetails.class))).thenReturn("token- eysgdsh");
        this.mockmvc.perform(post("/api/v1/authentication/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(loginDetails)))
                .andExpect(status().isOk());

    }
}
