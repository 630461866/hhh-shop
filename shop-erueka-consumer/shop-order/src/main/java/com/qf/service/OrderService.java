package com.qf.service;

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "shop-order-service")
public interface OrderService {

    @RequestMapping("order/insertOrder/{userId}")
    public ResultBean addOrder(@PathVariable("userId") String userId);

    @RequestMapping("updateOrder/{oderId}")
    public ResultBean updateOrderById(@PathVariable("oderId") String oderId);
}
