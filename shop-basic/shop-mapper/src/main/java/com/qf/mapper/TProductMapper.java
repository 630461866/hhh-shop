package com.qf.mapper;

import com.qf.entity.TProduct;

public interface TProductMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(TProduct record);

    int insertSelective(TProduct record);

    TProduct selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(TProduct record);

    int updateByPrimaryKey(TProduct record);



}