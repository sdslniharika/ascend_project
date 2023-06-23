package com.stackroute.customerservice.config;

import com.stackroute.customerservice.dto.BookingDetailsResponse;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private RabbitTemplate template;

    private DirectExchange exchange;

    @Autowired
    public Producer(RabbitTemplate template, DirectExchange exchange) {
        this.template = template;
        this.exchange = exchange;
    }

    public void sendMessagetoRabbitMq(BookingDetailsResponse bookingDetails)
    {
        template.convertAndSend(exchange.getName(),"bookingDetails_key",bookingDetails);
    }
}
