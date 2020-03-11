package com.qf.service;

import com.qf.entity.TProductType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "shop-product-service")
public interface IProductTypeService {

    @RequestMapping("index/show")
    List<TProductType> list();

}
