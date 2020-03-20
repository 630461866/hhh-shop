package com.qf.service;


import com.qf.dto.ResultBean;

public interface CartService {
    ResultBean addProduct(String id, Long productId, int count);

    ResultBean clean(String uuid);

    ResultBean update(String uuid, Long productId, int count);

    ResultBean showCart(String id);

    ResultBean merge(String uuid, String userId);

}
