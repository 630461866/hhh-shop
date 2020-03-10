package com.qf.service.impl;

import com.qf.entity.PCA;
import com.qf.mapper.PCAMapper;
import com.qf.service.PcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PcaServiceImpl implements PcaService{

    @Autowired
    private PCAMapper pcaMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return pcaMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PCA record) {
        return pcaMapper.insert(record);
    }

    @Override
    public int insertSelective(PCA record) {
        return pcaMapper.insertSelective(record);
    }

    @Override
    public PCA selectByPrimaryKey(Integer id) {
        return pcaMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PCA record) {
        return pcaMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PCA record) {
        return pcaMapper.updateByPrimaryKey(record);
    }
}
