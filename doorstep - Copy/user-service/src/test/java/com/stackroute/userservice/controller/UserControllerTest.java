package com.stackroute.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.model.UserEntity;
import com.stackroute.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static com.stackroute.userservice.model.Role.USER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserEntity userEntity;


    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        userEntity = UserEntity.builder().emailId("testing@gmail.com").password("123456").customerId("1234").firstName("vishnu").middleName("vardhan").lastName("reddy").
                mobileNo("1234567890").dob("12-12-96").doorNo("12-2").street("tirupati").landmark("busstand").city("tpt").dist("Tirupati").state("ap").pinCode(517112).role(USER).build();


    }


    @Test
    public void createUserProfile() throws Exception {
        when(userService.createProfile(any())).thenReturn(userEntity);
        mockMvc.perform(post("/api/v1/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

    }

    //        @Test
//        public void saveUserTestFailure() throws Exception {
//            when(userService.createProfile(any())).thenThrow(ProfileAlreadyExist.class);
//            mockMvc.perform(post("/api/v1/addUser")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(objectMapper.writeValueAsString(userEntity)))
//                    .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
//        }
    @Test
    public void getAllUsers() throws Exception {
        mockMvc.perform(get("/api/v1/AllUsers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getUserByEmailId() throws Exception {
        Mockito.when(userService.getUserByEmailId(Mockito.any())).thenReturn(userEntity);
        mockMvc.perform(get("/api/v1/findByEmailId/testing@gmail.com"))
                .andExpect(status().isOk());
    }

}

