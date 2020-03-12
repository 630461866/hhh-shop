package com.qf.shopsso.service;

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: zhangjia
 * @Date: 2020/3/12 22:44
 */
@FeignClient( value = "shop-sso-service")
public interface IUserservice {

    @RequestMapping("checkIsLogin")
    ResultBean checkLogin(@RequestParam("uname") String uname,@RequestParam("password") String password);
}
