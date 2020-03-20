package com.qf.service;

import com.qf.dto.ResultBean;

public interface SearchProductService {

    public ResultBean selectByPrimaryKey(Long id);


    void updateProductCount(int orderId);
}
