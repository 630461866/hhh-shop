package com.qf.ssoservice.controller;



import com.qf.constant.CookieConstant;
import com.qf.dto.ResultBean;
import com.qf.ssoservice.service.IUserservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @Autowired
    private IUserservice userservice;

    @RequestMapping("user/checkLogin")
    @ResponseBody
    public ResultBean checkLogin(String uname, String password){
       return userservice.checkLogin(uname,password);
    }

    /**
     * 判断用户是否已登录
     */
    @RequestMapping("user/checkIsLogin")
    @ResponseBody
    public ResultBean checkIsLogin(@CookieValue(name = CookieConstant.USER_LOGIN,required = false) String uuid, HttpServletRequest request){

        return userservice.checkIsLogin(uuid);

    }
}
