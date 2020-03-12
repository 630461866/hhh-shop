package com.qf.controller;


import com.qf.dto.ResultBean;
import com.qf.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FindController {

    @Autowired
    private ISearchService searchService;

    @RequestMapping("search")
    @ResponseBody
    public ResultBean searchByKeyword(String keyword){

        ResultBean resultBean = searchService.searchByKeyword(keyword);

        return resultBean;

    }

    @RequestMapping("addProduct")
    @ResponseBody
    public ResultBean addProduct(Long pid){
        return searchService.addProduct(pid);
    }

}
