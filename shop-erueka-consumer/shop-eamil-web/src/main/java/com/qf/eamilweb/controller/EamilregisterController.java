package com.qf.eamilweb.controller;

import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.eamilweb.service.IemailService;
import com.qf.util.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhangjia
 * @Date: 2020/3/10 21:17
 */
@RestController
@RequestMapping("user")
public class EamilregisterController {

   /* @Autowired
    private IemailService service;
*/
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("regist/email/{addr}/{password}")
    public ResultBean registByEmail(@PathVariable String addr,
                                    @PathVariable String password){
        //1.生成uuid
        String uuid = UUID.randomUUID().toString();
        //2.发邮件  通过ribbon来调用
        String url = String.format("http://email-service/email/send/%s/%s", addr, uuid);
        ResultBean resultBean = restTemplate.getForObject(url, ResultBean.class);
        ResultBean result = null;
        if(resultBean.getErrno()==0){
            //3.url组织好的键值对存到redis中
            //往redis中存入这样的键值对： regist:email:url:128731-sdfl-123 = addr
            redisTemplate.opsForValue().set(RedisUtil.getRedisKey(
                    RedisConstant.REGIST_EMAIL_URL_KEY_PRE,
                    uuid),addr,15, TimeUnit.MINUTES);
            //4.去数据库中创建该用户
//            result = restTemplate.getForObject(String.format("http://regist-service/user/regist/%s/%s", addr, password), ResultBean.class);
        }

        return ResultBean.success("注册成功");
    }

}
