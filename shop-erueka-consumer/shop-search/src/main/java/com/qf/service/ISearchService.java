package com.qf.service;

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("shop-search-service")
public interface ISearchService {

    @RequestMapping("search")
    ResultBean searchByKeyword(String keyword);
}
