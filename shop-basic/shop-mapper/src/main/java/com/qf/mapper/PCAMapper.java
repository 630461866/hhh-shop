package com.qf.mapper;

import com.qf.entity.PCA;

public interface PCAMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PCA record);

    int insertSelective(PCA record);

    PCA selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PCA record);

    int updateByPrimaryKey(PCA record);
}