package com.qf.shopemailservice.config;

import com.qf.constant.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue getQueue(){
        return new Queue(RabbitConstant.EMAIL_SEND_QUEUE);
    }

    //topic
    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(RabbitConstant.EMAIL_TOPIC_EXCHANGE);
    }

    //设置队列和交换机的绑定关系
    @Bean
    public Binding getBinding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("email.regist");
    }



}
