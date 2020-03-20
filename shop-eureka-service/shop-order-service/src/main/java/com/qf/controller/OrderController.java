package com.qf.controller;

import com.qf.dto.ResultBean;
import com.qf.service.OrderDetailService;
import com.qf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("insertOrder/{userId}")
    @ResponseBody
    public ResultBean addOrder(@PathVariable String userId){
        return  orderService.addOrder(userId);
    }

    @RequestMapping("updateOrder/{oderId}")
    public ResultBean updateOrderById(@PathVariable String oderId){
        int orderId = Integer.parseInt(oderId);
        return orderService.updateOrderById(orderId);
    }
}
