package com.qf.ssoservice.service.impl;

import com.qf.dto.ResultBean;
import com.qf.entity.TUser;
import com.qf.mapper.TUserMapper;
import com.qf.ssoservice.service.IUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: zhangjia
 * @Date: 2020/3/12 23:20
 */
@Service
public class UserServiceImpl implements IUserservice {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private TUserMapper mapper;

    @Override
    public ResultBean checkLogin(String uname, String password) {
        TUser user = mapper.selectByUsername(uname);
        if(user!=null){
            if(password!=null&&!"".equals(password)&&encoder.matches(password,user.getPassword())){
                //这里使用spring security
                return ResultBean.success(user,"登录成功");

            }
        }
        return ResultBean.error("用户名或密码错误");
    }
}
