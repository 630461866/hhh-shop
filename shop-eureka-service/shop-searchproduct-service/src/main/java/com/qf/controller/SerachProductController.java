package com.qf.controller;

import com.qf.dto.ResultBean;
import com.qf.service.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("searchproduct")
public class SerachProductController {
    @Autowired
    private SearchProductService searchProductService;

    @RequestMapping("product/{id}")
    @ResponseBody
    public ResultBean getProductById(@PathVariable String id){
        long lid = Long.parseLong(id);
        return searchProductService.selectByPrimaryKey(lid);
    }
}
