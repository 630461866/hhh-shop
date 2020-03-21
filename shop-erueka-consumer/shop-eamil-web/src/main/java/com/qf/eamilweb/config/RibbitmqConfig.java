package com.qf.eamilweb.config;

import com.qf.constant.RabbitConstant;
import org.springframework.amqp.core.TopicExchange;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RibbitmqConfig {

    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange(RabbitConstant.EMAIL_TOPIC_EXCHANGE);
    }
}
