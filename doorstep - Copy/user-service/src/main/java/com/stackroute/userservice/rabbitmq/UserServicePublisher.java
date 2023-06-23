package com.stackroute.userservice.rabbitmq;

import com.stackroute.userservice.model.UserModel;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserServicePublisher {

    private RabbitTemplate template;

    private DirectExchange exchange;

    @Autowired
    public UserServicePublisher(RabbitTemplate template, DirectExchange exchange) {
        this.template = template;
        this.exchange = exchange;
    }

    public void sendMessagetoRabbitMq(UserModel userModel) {
        template.convertAndSend(exchange.getName(), "userDetails_key", userModel);
    }
}

