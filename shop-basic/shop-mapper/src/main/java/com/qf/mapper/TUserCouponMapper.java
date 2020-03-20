package com.qf.mapper;

import com.qf.entity.TUserCoupon;

import java.util.List;

public interface TUserCouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserCoupon record);

    int insertSelective(TUserCoupon record);

    TUserCoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserCoupon record);

    int updateByPrimaryKey(TUserCoupon record);

    List<TUserCoupon> queryUserCouponByUserId(int uId);
}