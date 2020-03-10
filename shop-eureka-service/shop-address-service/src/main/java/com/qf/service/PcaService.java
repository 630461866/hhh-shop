package com.qf.service;

import com.qf.entity.PCA;

public interface PcaService {
    int deleteByPrimaryKey(Integer id);

    int insert(PCA record);

    int insertSelective(PCA record);

    PCA selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PCA record);

    int updateByPrimaryKey(PCA record);
}
