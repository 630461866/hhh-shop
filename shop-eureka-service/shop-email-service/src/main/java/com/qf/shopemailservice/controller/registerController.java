package com.qf.shopemailservice.controller;

import com.qf.dto.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zhangjia
 * @Date: 2020/3/16 18:23
 */
@Controller
@RequestMapping("email")
public class registerController {


    @RequestMapping("active")
    @ResponseBody
    public ResultBean actionAccount(String uuid){
        return ResultBean.success(uuid);
    }
}
