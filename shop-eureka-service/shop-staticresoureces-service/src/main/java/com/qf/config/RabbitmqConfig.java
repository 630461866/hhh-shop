package com.qf.config;

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
        return new Queue(RabbitConstant.PRODUCT_ADD_TO_RESOURCES_QUEUE);
    }

    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange(RabbitConstant.PRODUCT_ADD_TOPIC_EXCHANGE);
    }

    @Bean
    public Binding getBinding(Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("product.add");
    }

}
