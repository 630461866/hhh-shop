package com.qf.mapper;

import com.qf.entity.TProductType;

public interface TProductTypeMapper {
    int deleteByPrimaryKey(Long cid);

    int insert(TProductType record);

    int insertSelective(TProductType record);

    TProductType selectByPrimaryKey(Long cid);

    int updateByPrimaryKeySelective(TProductType record);

    int updateByPrimaryKey(TProductType record);
}