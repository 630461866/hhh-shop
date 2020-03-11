package com.qf.phoneregisterservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




/**
 * @Author: zhangjia
 * @Date: 2020/3/10 17:06
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue getQueue(){
        return new Queue("sms_send_queue");
    }

    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange("my-phone-register");
    }

    @Bean
    public Binding getBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("sms.send");
    }
}
