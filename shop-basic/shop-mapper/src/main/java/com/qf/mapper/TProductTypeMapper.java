package com.qf.mapper;

import com.qf.entity.TProductType;

import java.util.List;

public interface TProductTypeMapper {
    int deleteByPrimaryKey(Long cid);

    int insert(TProductType record);

    int insertSelective(TProductType record);

    TProductType selectByPrimaryKey(Long cid);

    int updateByPrimaryKeySelective(TProductType record);

    int updateByPrimaryKey(TProductType record);

    List<TProductType> list();
}