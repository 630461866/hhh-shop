package com.qf.service;

import com.qf.entity.TProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "shop-product-service")
public interface IProductService {

    @RequestMapping("index/show")
    List<TProduct> list();

}
