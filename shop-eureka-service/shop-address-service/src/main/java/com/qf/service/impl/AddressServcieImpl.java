package com.qf.service.impl;

import com.qf.service.AddressServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServcieImpl implements AddressServcie{
    @Autowired
    private TAddressMapper tAddressMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TAddress record) {
        return tAddressMapper.insert(record);
    }

    @Override
    public int insertSelective(TAddress record) {
        return tAddressMapper.insertSelective(record);
    }

    @Override
    public TAddress selectByPrimaryKey(Integer id) {
        return tAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TAddress record) {
        return tAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TAddress record) {
        return tAddressMapper.updateByPrimaryKey(record);
    }
}
