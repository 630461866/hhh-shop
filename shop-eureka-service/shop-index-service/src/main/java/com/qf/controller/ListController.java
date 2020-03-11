package com.qf.controller;

import com.qf.entity.TProductType;
import com.qf.service.IProductTypeService;
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
    private IProductTypeService productTypeService;

    @RequestMapping("show")
    @ResponseBody
    public List<TProductType> show(Model model){
        List<TProductType> list = productTypeService.list();
        return list;
    }

}
