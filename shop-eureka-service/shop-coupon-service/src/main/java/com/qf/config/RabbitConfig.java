package com.qf.config;


import com.qf.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queueOne(){
        return new Queue(RabbitConstant.ORDER_QUEUE,true,false,false,null);
    }

    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(RabbitConstant.ORDER_EXCHANGE);
    }

    @Bean
    public Binding bindOne(TopicExchange getTopicExchange,Queue queueOne){
        return BindingBuilder.bind(queueOne).to(getTopicExchange).with("order.*");
    }
}
