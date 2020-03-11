package com.qf.controller;


import com.qf.dto.ResultBean;
import com.qf.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FindController {

    @Autowired
    private ISearchService searchService;

    @RequestMapping("search")
    public ResultBean searchByKeyword(String keyword){

        ResultBean resultBean = searchService.searchByKeyword(keyword);

        return resultBean;

    }

}
