package com.qf.service.impl;

import com.qf.entity.TProduct;
import com.qf.mapper.TProductMapper;
import com.qf.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private TProductMapper productMapper;

    @Override
    public List<TProduct> list() {
        return productMapper.list();
    }
}
