package com.qf.phoneregister.controller;

import com.qf.dto.ResultBean;
import com.qf.phoneregister.service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zhangjia
 * @Date: 2020/3/10 10:55
 */
@Controller
@RequestMapping("user")
public class RegisterController {

    @Autowired
    private IUserService userService;

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
    /*
     * 实现注册
     * @return ResultBean 返回的结果包含了验证码错误的结果
     */
    @RequestMapping("doRegist")
    @ResponseBody
    public ResultBean doRegis(@RequestParam("phone") String phone,@RequestParam("code") String code,@RequestParam(value = "password") String password){
        return userService.regist(phone,code,password);

    }


}
