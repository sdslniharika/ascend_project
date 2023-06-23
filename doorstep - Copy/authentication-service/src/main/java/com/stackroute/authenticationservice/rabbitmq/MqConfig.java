package com.stackroute.authenticationservice.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MqConfig {
    public static final String QUEUE = "USER_SERVICE__QUEUE";
    public static final String EXCHANGE = "userDetails_exchange";

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(EXCHANGE);
    }
    @Bean
    public Queue registerQueue()
    {
        return new Queue(QUEUE,false);
    }
    @Bean
    Binding bindingUser(Queue registerQueue, DirectExchange exchange)
    {
        return BindingBuilder.bind(registerQueue()).to(exchange).with("userDetails_key");
    }
}
