package com.qf.phoneregisterservice.service;

import com.qf.dto.ResultBean;


/**
 * @Author: zhangjia
 * @Date: 2020/3/11 20:49
 */
public interface IUserService {

    ResultBean regist(String phone, String code, String password);
}
