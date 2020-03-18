package com.qf.shopemailservice.service.impl;


import com.qf.constant.RabbitConstant;
import com.qf.constant.RedisConstant;
import com.qf.dto.EmailMessageDTO;
import com.qf.dto.ResultBean;
import com.qf.entity.TUser;

import com.qf.mapper.TUserMapper;

import com.qf.shopemailservice.service.IemailService;
import com.qf.util.SpringSecurityUtil;
import com.qf.util.StringUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RegistServiceImpl implements IemailService {

    @Autowired
    private TUserMapper mapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${activeAccountServer}")
    String activeAccountServer;



    //邮箱注册功能的实现
    @Override
    public ResultBean registByEmail(String email, String password) {
        try {
            //1.发邮件
            EmailMessageDTO message = new EmailMessageDTO();//里面有两样东西： username   url
            message.setEmail(email);
            //生成uuid
            String uuid = UUID.randomUUID().toString();
            //创建url
            String url = activeAccountServer+uuid;
            message.setUrl(url);
            rabbitTemplate.convertAndSend(RabbitConstant.EMAIL_TOPIC_EXCHANGE,"email.regist",message);
            //2.将数据插入到数据库中
            TUser user = new TUser();
            user.setEmail(email);
            /* 向user对象中存入加密的password以及未加密的邮箱账号*/
            user.setPassword(SpringSecurityUtil.getEncodePassword(password));
            mapper.insertSelective(user);
            //3.存入到redis中
            //组织键
            String redisKey = StringUtil.getRedisKey(RedisConstant.REGISTER_EMAIL, uuid);
            redisTemplate.opsForValue().set(redisKey,email,10, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.error("注册失败");

        }


        return ResultBean.success("注册成功");
    }
}
