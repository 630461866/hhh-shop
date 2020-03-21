package com.qf.shopsso.controller;

import com.qf.constant.CookieConstant;
import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.shopsso.service.IUserservice;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhangjia
 * @Date: 2020/3/12 22:43
 */
@Controller
@RequestMapping("user")
@CrossOrigin(origins = "*", maxAge = 3600,allowCredentials = "true")
public class LoginController {

    @Autowired
    private IUserservice userService;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("showLogin")
    public String showLogin(){
        return "login";
    }

    @RequestMapping("showindex")
    public String showindex(){
        return "index";
    }

    //验证用户名和密码是否正确
    @RequestMapping("checkLogin")
    public String checkLogin(String uname, String password, HttpServletResponse response,
                             @CookieValue(name = CookieConstant.USER_CART,required = false) String userCartUnid){

        //交给service去验证用户名和密码是否正确
        ResultBean resultBean = userService.checkLogin(uname,password);
        if(resultBean.getErrno()==0){
            //登录成功
            //生成cookie
            String uuid = UUID.randomUUID().toString();
            Cookie cookie = new Cookie(CookieConstant.USER_LOGIN,uuid);
            //往redis里存
            //组织键
            String key = StringUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
            //把登录成功后的用户对象存入到redis中。以便checkIsLogin接口去判断是否已登录 来使用
            redisTemplate.opsForValue().set(key,resultBean.getData(),30, TimeUnit.DAYS);
            //cookie要发送给客户端
            cookie.setMaxAge(30*24*60*60);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return "redirect:http://localhost:9091";
//            return "redirect:showindex";

        }
        return "redirect:showLogin";
    }

    /**
     * 判断用户是否已登录
     */
    @RequestMapping("checkIsLogin")
    @ResponseBody
    public ResultBean checkIsLogin(@CookieValue(name = CookieConstant.USER_LOGIN,required = false) String uuid, HttpServletRequest request){
        //user_login
        //验证我当前是否已登录
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(CookieConstant.USER_LOGIN.equals(cookie.getName())){
                    //找到这个cookie
                    //拿到cookie的值，组织redis的键
                     uuid = cookie.getValue();
                    String redisKey = StringUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
                    Object o = redisTemplate.opsForValue().get(redisKey);
                    if(o!=null){
                        return ResultBean.success(o,"用户已登录");
                    }
                }
            }
        }
        return userService.checkIsLogin(uuid);

    }



}
