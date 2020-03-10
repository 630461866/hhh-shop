package com.qf.service;

import com.qf.entity.TAddress;

public interface AddressServcie {
    int deleteByPrimaryKey(Integer id);

    int insert(TAddress record);

    int insertSelective(TAddress record);

    TAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAddress record);

    int updateByPrimaryKey(TAddress record);
}
