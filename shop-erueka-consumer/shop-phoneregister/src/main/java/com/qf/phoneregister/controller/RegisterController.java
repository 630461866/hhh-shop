package com.qf.phoneregister.controller;

import com.qf.dto.ResultBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zhangjia
 * @Date: 2020/3/10 10:55
 */
@Controller
@RequestMapping("user")
public class RegisterController {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RequestMapping("register")
    public String getRegister(){
        return "register";
    }

    //发送短信验证码
    @RequestMapping("getCode")
    @ResponseBody
    public  ResultBean getCode(String phone){
        rabbitTemplate.convertAndSend("my-phone-register","sms.send",phone);
        return ResultBean.success();
    }


}
