package com.stackroute.userservice.serviceTest;

import com.stackroute.userservice.Repository.UserRepository;
import com.stackroute.userservice.exceptions.ProfileAlreadyExist;
import com.stackroute.userservice.model.UserEntity;
import com.stackroute.userservice.rabbitmq.UserServicePublisher;
import com.stackroute.userservice.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.stackroute.userservice.model.Role.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    UserServicePublisher userServicePublisher;
    @Mock
    UserRepository repository;
    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity userEntity;

    @BeforeEach
    public void setup() {
        userEntity = UserEntity.builder().emailId("testing@gmail.com").password("123456").
                customerId("1234").firstName("kusuma").middleName("sree").lastName("matam").
                mobileNo("1234567890").dob("12-12-96").doorNo("12-2").street("tirupati").
                landmark("ismahal").city("tpt").dist("Tirupati").state("ap").pinCode(517112).role(USER).build();
    }


    @Test
    public void saveUserTest() throws ProfileAlreadyExist {
        when(repository.save(Mockito.any())).thenReturn(userEntity);
        UserEntity user = userService.createProfile(userEntity);

        assertEquals(user.getEmailId(), userEntity.getEmailId());
        assertFalse(user.toString().isBlank());
    }

    @Test
    void getAllUsersByEmailIdTest() throws Exception {

        when(repository.findByEmailId(Mockito.any())).thenReturn(userEntity);
        UserEntity userDetails = userService.getUserByEmailId("testing@gmail.com");
        assertEquals("testing@gmail.com", userDetails.getEmailId());
    }

}


