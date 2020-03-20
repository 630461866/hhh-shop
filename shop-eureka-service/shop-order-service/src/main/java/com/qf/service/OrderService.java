package com.qf.service;

import com.qf.dto.ResultBean;
import com.qf.entity.TOrder;

public interface OrderService {

    int deleteByPrimaryKey(Integer id);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    TOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);

    ResultBean addOrder(String userId);

    ResultBean updateOrderById(int orderId);
}
