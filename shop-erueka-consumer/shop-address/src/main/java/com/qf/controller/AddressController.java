package com.qf.controller;

import com.google.gson.Gson;
import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.PCA;
import com.qf.entity.TAddress;
import com.qf.service.AddressService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("queryaddress/{userId}")
    public String getAddressList(@PathVariable String userId, HttpServletRequest request, Model model){
        System.out.println(userId);
        //1.验证用户是否已登录。获取登录用户主键
        Object o = request.getAttribute("user");
      /*  if(o == null){
            //================未登录,则返回登录页面======================= redis:    user:cart:userId
           String url = "http://shop-sso/user/showLogin/";
            return "forward:http://localhost:8090/user/showLogin/";
        }
        //若已登录，获取登录的对象Id*/
        //TUser user = (TUser) o;
        //Long uId = user.getId();
        //System.out.println("登录的用户Uid为："+uId);
        ResultBean resultBean = addressService.queryAddressList(userId);
        List<TAddress> addresseslist= (List<TAddress>) resultBean.getData();
        //System.out.println(addresseslist);
        model.addAttribute("addressList",addresseslist);

        ResultBean resultBean1 = addressService.queryProList();
        List<PCA>  provincelist = (List<PCA>) resultBean1.getData();
        //System.out.println(provincelist);
        model.addAttribute("provinceList",provincelist);
        return "address";
    }
    @RequestMapping("getCityList")
    @ResponseBody
    public List<PCA> getCityList(String id){
      ResultBean resultBean = addressService.getCityListByProName(id);
      List<PCA> citylist = (List<PCA>) resultBean.getData();
      return citylist;
    }

    @RequestMapping("getAreaList")
    @ResponseBody
    public List<PCA> getAreaList(String id){
        ResultBean resultBean = addressService.getAreaListByProName(id);
        List<PCA> arealist = (List<PCA>) resultBean.getData();
        return arealist;
    }

    @RequestMapping("addaddress")
    public String addAddress(TAddress tAddress,HttpServletRequest request){
        //tAddress.setIsdefault("否");
        tAddress.setUserid(1);

        //addressService.addinsert(tAddress);
        String redisKey = StringUtil.getRedisKey(RedisConstant.ADDRESS_USERID, "" + tAddress.getUserid());
        Gson gson = new Gson();
        String addressjson = gson.toJson(tAddress);
        //System.out.println(addressjson);
        redisTemplate.opsForValue().set(redisKey,addressjson);

        ResultBean addinsert = addressService.addinsert(""+tAddress.getUserid());
        System.out.println(addinsert.getMessage());
        return "forward:queryaddress/1";
    }

    @RequestMapping("delete/{id}")
    public String deleteAddress(@PathVariable String id){

        ResultBean resultBean = addressService.deleteByPrimaryKey(id);
        return "redirect:/address/queryaddress/1";
    }

    @RequestMapping("setdefault/{id}")
    public String setDefaultAddress(@PathVariable String id){
        //System.out.println(id);
        ResultBean resultBean = addressService.updateAddressDefault(id);

        return "redirect:/address/queryaddress/1";
    }
}
