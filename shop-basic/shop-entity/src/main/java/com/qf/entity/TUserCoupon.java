package com.qf.entity;

public class TUserCoupon {
    private Integer id;

    private Integer userId;

    private Integer couponId;

    private Double redpacketSurplus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Double getRedpacketSurplus() {
        return redpacketSurplus;
    }

    public void setRedpacketSurplus(Double redpacketSurplus) {
        this.redpacketSurplus = redpacketSurplus;
    }
}