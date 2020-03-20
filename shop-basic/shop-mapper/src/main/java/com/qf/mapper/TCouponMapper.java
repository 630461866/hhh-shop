package com.qf.mapper;

import com.qf.entity.TCoupon;
import com.qf.entity.TUserCoupon;

import java.util.List;

public interface TCouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCoupon record);

    int insertSelective(TCoupon record);

    TCoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCoupon record);

    int updateByPrimaryKey(TCoupon record);

    List<TCoupon> queryCouponByUserId(int uId);
}