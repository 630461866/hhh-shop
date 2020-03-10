package com.qf.mapper;

import com.qf.entity.TProduct;

import java.util.List;

public interface TProductMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(TProduct record);

    int insertSelective(TProduct record);

    TProduct selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(TProduct record);

    int updateByPrimaryKey(TProduct record);


    List<TProduct> list();
}