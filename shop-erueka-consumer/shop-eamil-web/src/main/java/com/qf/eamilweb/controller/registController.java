package com.qf.eamilweb.controller;

import com.qf.constant.RabbitConstant;
import com.qf.dto.ResultBean;
import com.qf.eamilweb.service.IemailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhangjia
 * @Date: 2020/3/16 18:26
 */
@Controller
@RequestMapping("user")
@CrossOrigin(origins = "*", maxAge = 3600,allowCredentials = "true")
public class registController {

    @Autowired
    private IemailService service;


    @RequestMapping("showRegist")
    public String showRegist(){
        return "register";
    }


    @RequestMapping("registByEmail")
    public String registByEmail(String email,String password){
        ResultBean resultBean = service.registByEmail(email,password);
        if (resultBean.getErrno()==0) {
            //这里可以直接重定向到登陆页面
            return "redirect:http://localhost:8090/user/checkLogin";
        }
        return "redirect:user/showRegist";
    }

}
