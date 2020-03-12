package com.qf.phoneregister.service;

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: zhangjia
 * @Date: 2020/3/11 20:49
 */
@FeignClient(value = "shop-phoneregister-server")
public interface IUserService {

    @RequestMapping("user/doRegist")
    ResultBean regist(@RequestParam(value = "phone") String phone,@RequestParam("code") String code,@RequestParam(value = "password") String password);
}
