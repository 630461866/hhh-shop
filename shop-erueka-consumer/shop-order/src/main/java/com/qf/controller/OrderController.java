package com.qf.controller;

import com.google.gson.Gson;
import com.qf.constant.RabbitConstant;
import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.dto.TProductCartDTO;
import com.qf.entity.*;
import com.qf.service.OrderService;
import com.qf.service.SearchProductService;
import com.qf.util.StringUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private SearchProductService searchProductService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("pay/{userId}")
    public String toPay(@PathVariable String userId,  HttpServletRequest request, Model model){
        //@RequestParam(value = "productIdList[]") List<String> productIdList,
        //1.验证用户是否已登录。获取登录用户主键
        Object o = request.getAttribute("user");
      /*  if(o == null){
            //================未登录,则返回登录页面======================= redis:    user:cart:userId
           String url = "http://shop-sso/user/showLogin/";
            return "forward:http://localhost:8090/user/showLogin/";
        }
        //若已登录，获取登录的对象Id*/
        //TUser user = (TUser) o;
        //Long userId = user.getId();
        //System.out.println("登录的用户Uid为："+userId);

        //2.通过用户主键，使用ribbon调用地址服务，获取该用户的收货地址集合
        String url1 = "http://shop-address-service/address/queryaddress/"+userId;
        ResultBean resultBean1 = restTemplate.getForObject(url1, ResultBean.class);

        List<TAddress> addressList = (List<TAddress>) resultBean1.getData();
        /*for (TAddress tAddress : addressList) {
            System.out.println(tAddress.getAddressDesc());
        }*/

        //3.获取用户的红包及优惠券信息
        //查用户优惠券
        String url2 = "http://shop-coupon-service/coupon/querycoupon/"+userId;
        ResultBean resultBean2 = restTemplate.getForObject(url2, ResultBean.class);
        Map<String,Object> map = (Map<String, Object>) resultBean2.getData();

        List<TCoupon> couponlist = (List<TCoupon>) map.get("couponlist");
        //System.out.println(couponlist);
        List<TUserCoupon> usercouponlist = (List<TUserCoupon>) map.get("usercouponlist");
        //System.out.println(usercouponlist);

        //4.获取用户购买的商品id集合
 /*       List<TProductCartDTO> list = new ArrayList<>();
        TProductCartDTO tProductCartDTO = new TProductCartDTO();
        //根据userId从redis中获取购物车集合，遍历购物车集合，与商品id集合比较，
        // 若相等，则调用商品服务获取对象，将商品数量及商品对象存入TProductCartDTO对象，在存入list集合
        String redisKey = StringUtil.getRedisKey(RedisConstant.USER_CART_PRE, userId);
        List<CartItem> cartslist = (List<CartItem>) redisTemplate.opsForValue().get(redisKey);

        for(int i=0;i<cartslist.size();i++){
            for (int j=0;j<productIdList.size();j++){
                Long pid = cartslist.get(i).getProductId();
                    if (pid==productIdList.get(j)){
                            tProductCartDTO.setCount(cartslist.get(i).getCount());
                            tProductCartDTO.setUpdateTime(cartslist.get(i).getUpdateTime());
                            //调用商品服务，根据商品id获得对象，存入tProductCartDTO
                        ResultBean productById = searchProductService.getProductById(""+pid);
                        TProduct  product = (TProduct) productById.getData();
                        tProductCartDTO.setProduct(product);
                        list.add(tProductCartDTO);
                    }
            }
        }
        //计算商品总价格,并存入model
            BigDecimal totalPrice = new BigDecimal(String.valueOf(0));
            for (TProductCartDTO product : list) {
                totalPrice = totalPrice.add(new BigDecimal(String.valueOf(product.getDanPrice())));
            }
        //保留两位小数
        Double talPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        String userpaykey = StringUtil.getRedisKey(RedisConstant.USER_CART_PAY, userId);
        //将选中的待购买商品集合存入redis
        redisTemplate.opsForValue().set(userpaykey,list);*/
        //---------------------------------------测试数据----------------------分割线------------------------------------
        List<TProductCartDTO> list = new ArrayList<>();
        List<String> productIdList = new ArrayList<>();
        Double talPrice = 0.00;

            TProductCartDTO tProductCartDTO = new TProductCartDTO();
            //前端传过来的商品id集合
            productIdList.add("1");
            productIdList.add("679532");
            productIdList.add("844022");
            for (String s : productIdList) {
                long pid = Long.parseLong(s);
                ResultBean productById = searchProductService.getProductById(""+pid);
                Gson gson = new Gson();
                String  productjson = (String) productById.getData();
                TProduct product = gson.fromJson(productjson, TProduct.class);
                tProductCartDTO.setProduct(product); //封装商品对象
                tProductCartDTO.setCount(4);
                list.add(tProductCartDTO);
            }
            //计算商品总价格,并存入model
            BigDecimal totalPrice = new BigDecimal(String.valueOf(0));
            for (TProductCartDTO product : list) {
                totalPrice = totalPrice.add(new BigDecimal(String.valueOf(product.getDanPrice())));
            }
            //保留两位小数
            talPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            //将选中的待购买商品集合存入redis
            String userpaykey = StringUtil.getRedisKey(RedisConstant.USER_CART_PAY, userId);
            redisTemplate.opsForValue().set(userpaykey,list);

        //---------------------------------------------测试数据-------------------------------------------------------------
        //5.将商品信息，收货地址信息，红包优惠券信息，传入前端页面
        String queryAddressUrl = "http://localhost:7002/address/queryaddress/"+userId;
        System.out.println(list);
        model.addAttribute("addressList",addressList);
        model.addAttribute("couponList",couponlist);
        model.addAttribute("usercouponList",usercouponlist);
        model.addAttribute("totalPrice",talPrice);
        model.addAttribute("userPayList",list);
        model.addAttribute("queryAddressUrl",queryAddressUrl);
        return "pay";
    }

    @RequestMapping("creatOrder")
    public void toCreatOrder(TOrder tOrder,HttpServletRequest request){
        //1.验证用户是否已登录。获取登录用户主键
        Object o = request.getAttribute("user");
        //获取登录的对象Id
        //TUser user = (TUser) o;
        //Long userId = user.getId();
        //System.out.println("登录的用户Uid为："+userId);

        Long userId = 1L;
         if (tOrder!=null){
             tOrder.setoUserid(1);
             tOrder.setoStatus(0);
             tOrder.setoOrderdate(new Date());
             String redisKey = StringUtil.getRedisKey(RedisConstant.ORDER_USER, "" + userId);
             Gson gson = new Gson();
             String orderjson = gson.toJson(tOrder);
             redisTemplate.opsForValue().set(redisKey,orderjson);
             System.out.println(orderjson);
             //创建订单
             ResultBean resultBean = orderService.addOrder("" + userId);
            //发布rabbitmq消息，进行异步商品减库存，订单详情页生成，用户消费的优惠券及红包的更新
             int orderId = resultBean.getErrno();
             rabbitTemplate.convertAndSend(RabbitConstant.ORDER_EXCHANGE,"order.orderId",orderId);

             if (resultBean.getErrno()>0){
                 //创建订单成功，调用支付接口完成支付
                 String url1 = "http://shop-pay-service/pay/doPay/"+resultBean.getErrno();
                 String string = restTemplate.getForObject(url1,String.class);
             }else {
                 System.out.println(resultBean.getMessage());
             }
         }
       // return "forward:/order/pay/1";
    }

    @RequestMapping("updateOrder/{oderId}")
    public String updateOrder(@PathVariable("oderId") String oderId){
        ResultBean resultBean = orderService.updateOrderById(oderId);
        if (resultBean.getErrno()>0){
            System.out.println(resultBean.getMessage());
            return resultBean.getMessage();
        }
        System.out.println(resultBean.getMessage());
        return resultBean.getMessage();
    }

}
