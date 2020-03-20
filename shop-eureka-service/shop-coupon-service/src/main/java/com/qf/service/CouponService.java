package com.qf.service;

import com.qf.dto.ResultBean;

public interface CouponService {
    ResultBean queryCouponByUserId(String userId);

    void updateCouponById(int orderId);
}
