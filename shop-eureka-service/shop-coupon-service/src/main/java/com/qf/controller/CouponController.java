package com.qf.controller;

import com.qf.dto.ResultBean;
import com.qf.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @RequestMapping("querycoupon/{userId}")
    @ResponseBody
    public ResultBean queryCouponByUserId(@PathVariable String userId){
        ResultBean resultBean =couponService.queryCouponByUserId(userId);
        return resultBean;
    }
}
