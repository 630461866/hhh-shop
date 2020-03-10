package com.qf.controller;

import com.qf.service.OrderDetailService;
import com.qf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("addOrder")
    public void addOrder(){

    }
}
