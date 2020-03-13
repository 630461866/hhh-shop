package com.qf.service;

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: zhangjia
 * @Date: 2020/3/13 16:14
 */
@FeignClient(value = "shop-sso-service")
public interface IUserService {

    @RequestMapping("user/checkIsLogin")
    ResultBean checkIsLogin(@RequestParam("uuid") String uuid);
}
