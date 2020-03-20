package com.qf.consumer;

import com.qf.constant.RabbitConstant;
import com.qf.service.CouponService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchConsumer {
    @Autowired
    private CouponService couponService;


    @RabbitListener(queues = RabbitConstant.ORDER_QUEUE)
    public void recrive(int orderId){
        couponService.updateCouponById(orderId);
    }

}
