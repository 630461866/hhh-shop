package com.qf.phoneregister.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhangjia
 * @Date: 2020/3/10 16:27
 */
@Configuration
public class RabbitmqConfig {

    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange("my-phone-register");
    }
}
