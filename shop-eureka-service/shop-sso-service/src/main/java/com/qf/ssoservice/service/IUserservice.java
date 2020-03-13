package com.qf.ssoservice.service;

import com.qf.dto.ResultBean;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author: zhangjia
 * @Date: 2020/3/12 22:44
 */
public interface IUserservice {

    ResultBean checkLogin( String uname, String password);

    ResultBean checkIsLogin(String uuid);
}
