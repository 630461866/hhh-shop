package com.qf.service.impl;

import com.qf.entity.TOrderDetail;
import com.qf.mapper.TOrderDetailMapper;
import com.qf.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private TOrderDetailMapper tOrderDetailMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tOrderDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TOrderDetail record) {
        return tOrderDetailMapper.insert(record);
    }

    @Override
    public int insertSelective(TOrderDetail record) {
        return tOrderDetailMapper.insertSelective(record);
    }

    @Override
    public TOrderDetail selectByPrimaryKey(Integer id) {
        return tOrderDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TOrderDetail record) {
        return tOrderDetailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TOrderDetail record) {
        return tOrderDetailMapper.updateByPrimaryKey(record);
    }
}
