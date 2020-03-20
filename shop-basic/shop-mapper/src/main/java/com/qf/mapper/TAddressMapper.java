package com.qf.mapper;

import com.qf.dto.ResultBean;
import com.qf.entity.TAddress;

import java.util.List;

public interface TAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAddress record);

    int insertSelective(TAddress record);

    TAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAddress record);

    int updateByPrimaryKey(TAddress record);

    List<TAddress> queryAddressList(int userId);

}