package com.qf.eamilweb.service;

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: zhangjia
 * @Date: 2020/3/10 21:18
 */
@FeignClient(value = "email-service")
public interface IemailService {

    //实现注册功能
    @RequestMapping("user/registByEmail")
    ResultBean registByEmail(@RequestParam("email") String email,@RequestParam("password") String password);

}
