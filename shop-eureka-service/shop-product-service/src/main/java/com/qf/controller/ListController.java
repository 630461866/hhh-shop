package com.qf.controller;

import com.qf.entity.TProduct;
import com.qf.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("index")
public class ListController {

    @Autowired
    private IProductService productService;

    @RequestMapping("show")
    @ResponseBody
    public List<TProduct> show(Model model){
        List<TProduct> list = productService.list();
        return list;
    }

}
