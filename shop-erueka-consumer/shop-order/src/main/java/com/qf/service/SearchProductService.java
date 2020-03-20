package com.qf.service;

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@FeignClient(value = "shop-searchproduct-service")
public interface SearchProductService {

    @RequestMapping("searchproduct/product/{id}")
    public ResultBean getProductById(@PathVariable("id") String id);

}
