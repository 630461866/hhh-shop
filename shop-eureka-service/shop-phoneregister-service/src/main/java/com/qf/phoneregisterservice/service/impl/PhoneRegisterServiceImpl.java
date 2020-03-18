package com.qf.phoneregisterservice.service.impl;


import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.TUser;
import com.qf.mapper.TUserMapper;
import com.qf.phoneregisterservice.service.IUserService;
import com.qf.util.SpringSecurityUtil;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: zhangjia
 * @Date: 2020/3/11 20:59
 */
@Service
public class PhoneRegisterServiceImpl  implements IUserService {

    @Autowired
    private TUserMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;


    //实现手机注册的功能
    @Override
    public ResultBean regist(String phone, String code, String password) {
        //得到redis中的phone和code
        String redisKey = StringUtil.getRedisKey(RedisConstant.REGISTER_PHONE, phone);
        String  redisCode= (String) redisTemplate.opsForValue().get(redisKey);
        System.out.println(redisCode);
        System.out.println(redisCode);
        System.out.println(redisCode);
        System.out.println(redisCode);
        //判断验证码是否正确
        if (code.equals(redisCode)){
            TUser user = new TUser();
           /* 向user对象中存入加密的password以及未加密的手机号*/
            user.setPassword(SpringSecurityUtil.getEncodePassword(password));
            user.setPhone(phone);
            //插入数据到数据库中
            mapper.insert(user);
            return ResultBean.success("注册成功");
        }

        return ResultBean.success("验证码错误");
    }
}
