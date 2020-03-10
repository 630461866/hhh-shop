package com.qf.service.impl;

import com.qf.entity.TOrder;
import com.qf.mapper.TOrderMapper;
import com.qf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private TOrderMapper tOrderMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TOrder record) {
        return tOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(TOrder record) {
        return tOrderMapper.insertSelective(record);
    }

    @Override
    public TOrder selectByPrimaryKey(Integer id) {
        return tOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TOrder record) {
        return tOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TOrder record) {
        return tOrderMapper.updateByPrimaryKey(record);
    }
}
