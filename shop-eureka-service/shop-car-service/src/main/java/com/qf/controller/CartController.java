package com.qf.controller;

import com.qf.constant.CookieConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.TUser;
import com.qf.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*", maxAge = 3600,allowCredentials = "true")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/add/{productId}/{count}")
    @ResponseBody
    public ResultBean addProduct(String id,
                @PathVariable Long productId,
        @PathVariable int count){
                return cartService.addProduct(id,productId,count);
    }


    @RequestMapping("/clean")
    @ResponseBody
    public ResultBean cleanCart(String id){
            return cartService.clean(id);
    }


    @RequestMapping("/update/{productId}/{count}")
    @ResponseBody
    public ResultBean updateCart(
            @PathVariable Long productId,
            @PathVariable int count,
            @CookieValue(name=CookieConstant.USER_CART,required = false)String uuid,
            HttpServletRequest request
    ){

        Object o = request.getAttribute("user");
        if(o!=null){
            //=============已登录状态下的购物车==============  user:cart:userId
            TUser user = (TUser) o;
            return cartService.update(user.getId().toString(),productId,count);

        }

        return cartService.update(uuid,productId,count);

    }



    @RequestMapping("/show")
    @ResponseBody
    public ResultBean showCart(String id,HttpServletRequest request){

        Object o = request.getAttribute("user");
        //============查看已登录状态下的购物车=============
        if(o!=null){
            TUser user = (TUser) o;
            Long userId = user.getId();
            return cartService.showCart(userId.toString());
        }
        //============查看未登录状态下的购物车=============
        return cartService.showCart(id);

    }

    @RequestMapping("/merge")
    @ResponseBody
    public ResultBean merge(@CookieValue(name = CookieConstant.USER_CART,required = false)String uuid,
                            HttpServletRequest request,HttpServletResponse response){
        //获得uuid,和uid
        TUser user = (TUser) request.getAttribute("user");
        String userId = null;
        if(user!=null){
            userId = user.getId().toString();
        }

        Cookie cookie = new Cookie(CookieConstant.USER_CART,"");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);


        return cartService.merge(uuid,userId);

    }
}
