package com.qf.service;

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("shop-search-service")
public interface ISearchService {

    @RequestMapping("search")
    ResultBean searchByKeyword(@RequestParam("keyword") String keyword);

    @RequestMapping("addProduct")
    ResultBean addProduct(@RequestParam("pid") Long pid);
}
