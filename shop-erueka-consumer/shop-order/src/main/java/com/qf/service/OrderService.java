package com.qf.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "order-product-service")
public interface OrderService {

    @RequestMapping("addOrder")
    public void addOrder();
}
