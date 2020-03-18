package com.qf.shopemailservice.controller;

import com.qf.dto.ResultBean;
import com.qf.shopemailservice.service.IemailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhangjia
 * @Date: 2020/3/16 18:58
 */
@Controller
public class emailController {

    @Autowired
    private IemailService service;

    @RequestMapping("user/registByEmail")
    public ResultBean registByEmail(String email,String password){
        return service.registByEmail(email,password);
    }
}
