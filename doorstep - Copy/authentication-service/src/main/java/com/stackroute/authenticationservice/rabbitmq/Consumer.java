package com.stackroute.authenticationservice.rabbitmq;

import com.stackroute.authenticationservice.entity.LoginDetails;
import com.stackroute.authenticationservice.entity.UserServiceResponse;
import com.stackroute.authenticationservice.service.AuthenticationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    AuthenticationService service;
    @RabbitListener(queues ="USER_SERVICE__QUEUE")
    public void getLoginDetailsInMq(UserServiceResponse userServiceResponse) {
        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setEmailId(userServiceResponse.getEmailId());
        loginDetails.setPassword(userServiceResponse.getPassword());
        loginDetails.setRole(userServiceResponse.getRole());
        service.save(loginDetails);

    }



}
