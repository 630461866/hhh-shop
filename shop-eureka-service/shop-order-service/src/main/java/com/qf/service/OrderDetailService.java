package com.qf.service;

import com.qf.entity.TOrderDetail;

public interface OrderDetailService {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrderDetail record);

    int insertSelective(TOrderDetail record);

    TOrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TOrderDetail record);

    int updateByPrimaryKey(TOrderDetail record);
}
