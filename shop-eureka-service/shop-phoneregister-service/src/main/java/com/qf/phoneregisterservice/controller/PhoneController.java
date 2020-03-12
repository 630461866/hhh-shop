package com.qf.phoneregisterservice.controller;

import com.qf.dto.ResultBean;
import com.qf.phoneregisterservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zhangjia
 * @Date: 2020/3/12 0:02
 */
@Controller
public class PhoneController {
    @Autowired
    private IUserService userService;

    @RequestMapping("user/doRegist")
    @ResponseBody
    public ResultBean regist(String phone, String code, String password){
        return userService.regist(phone,code,password);
    }


}
