package com.qf.controller;

import com.qf.constant.CookieConstant;
import com.qf.dto.ResultBean;
import com.qf.dto.TProductCartDTO;
import com.qf.entity.TUser;
import com.qf.service.CartService;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/add/{productId}/{count}")
    public String addProduct(@CookieValue(name = CookieConstant.USER_CART, required = false) String uuid,
                             @PathVariable Long productId,
                             @PathVariable int count,
                             HttpServletResponse response,
                             HttpServletRequest request, ModelMap modelMap){
        Object o = request.getAttribute("user");
        if(o!=null){
            TUser user = (TUser) o;
            Long userId = user.getId();
            ResultBean resultBean = cartService.addProduct(userId.toString(), productId, count);
            modelMap.put("msg",resultBean.getMessage());
            return "success";
        }

        if(uuid==null||"".equals(uuid)){
            uuid = UUID.randomUUID().toString();

            Cookie cookie = new Cookie(CookieConstant.USER_CART,uuid);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        System.out.println("***************"+uuid);
        ResultBean resultBean = cartService.addProduct(uuid,productId,count);
        modelMap.put("msg",resultBean.getMessage());
        return "success";

    }

    @RequestMapping("/clean")
    public String cleanCart(@CookieValue(name=CookieConstant.USER_CART,required = false)String uuid,HttpServletResponse response,
                                HttpServletRequest request,ModelMap modelMap){
        Object o = request.getAttribute("user");
        if(o!=null){
            TUser user = (TUser) o;
            ResultBean resultBean = cartService.cleanCart(user.getId().toString());
            modelMap.put("msg",resultBean.getMessage());
            return "forward:/cart/show";
        }
        
        if(uuid!=null&&!"".equals(uuid)){
            //删除Cookie
            Cookie cookie = new Cookie(CookieConstant.USER_CART,"");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            //删除redis中的购物车
            ResultBean resultBean = cartService.cleanCart(uuid);
            modelMap.put("msg",resultBean.getMessage());
            return "forward:/cart/show";
        }
        return "noCart";

    }

    @RequestMapping("/show")
    public String showCart(@CookieValue(name=CookieConstant.USER_CART,required = false)String uuid
            ,HttpServletRequest request,ModelMap modelMap){
        Object o = request.getAttribute("user");
        //============查看已登录状态下的购物车=============
        if(o!=null){
            TUser user = (TUser) o;
            Long userId = user.getId();
            ResultBean resultBean = cartService.showCart(userId.toString());
            List<TProductCartDTO> products =(List<TProductCartDTO>) resultBean.getData();
            System.out.println(products.size());
            modelMap.put("productList",products);
            return "shopcart";
        }
        //============查看未登录状态下的购物车=============
        System.out.println("----------------"+uuid);
        ResultBean resultBean = cartService.showCart(uuid);
        System.out.println(resultBean.getMessage());
        System.out.println(resultBean.getData());

        List<TProductCartDTO> products =(List<TProductCartDTO>) resultBean.getData();
        System.out.println(products.size());
        modelMap.put("productList",products);
        return "shopcart";

    }

    @RequestMapping("/update/{productId}/{count}")
    @ResponseBody
    public String updateCart(
            @PathVariable Long productId,
            @PathVariable int count,
            @CookieValue(name=CookieConstant.USER_CART,required = false)String uuid,
            HttpServletRequest request,ModelMap modelMap
    ){

        Object o = request.getAttribute("user");
        if(o!=null){
            //=============已登录状态下的购物车==============  user:cart:userId
            TUser user = (TUser) o;
            ResultBean resultBean = cartService.updateCart(productId, count, user.getId().toString());
            modelMap.put("msg",resultBean.getMessage());
            return "forward:/cart/show";

        }

        ResultBean resultBean = cartService.updateCart(productId, count, uuid);
        modelMap.put("msg",resultBean.getMessage());
        return "forward:/cart/show";

    }
}
