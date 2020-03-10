package com.qf.mapper;

import com.qf.entity.TOrder;

public interface TOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    TOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);
}