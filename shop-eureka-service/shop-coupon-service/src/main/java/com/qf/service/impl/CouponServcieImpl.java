package com.qf.service.impl;

import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.dto.TProductCartDTO;
import com.qf.entity.TCoupon;
import com.qf.entity.TOrder;
import com.qf.entity.TProduct;
import com.qf.entity.TUserCoupon;
import com.qf.mapper.TCouponMapper;
import com.qf.mapper.TUserCouponMapper;
import com.qf.service.CouponService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponServcieImpl implements CouponService{

    @Autowired
    private TCouponMapper tCouponMapper;
    @Autowired
    private TUserCouponMapper tUserCouponMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultBean queryCouponByUserId(String userId) {
        ResultBean resultBean = new ResultBean();
        if (userId!=null){
            int uId = Integer.parseInt(userId);
            //查优惠券红包
           List<TCoupon> couponlist= tCouponMapper.queryCouponByUserId(uId);
           resultBean.setMessage("获取用户折扣券成功");
           //查红包
           List<TUserCoupon> tUserCouponlist= tUserCouponMapper.queryUserCouponByUserId(uId);
            Map<String,Object> map = new HashMap<>();
            map.put("couponlist",couponlist);
            map.put("usercouponlist",tUserCouponlist);
           resultBean.setData(map);
           return resultBean;

        }
        resultBean.setErrno(0000);
        resultBean.setMessage("获取折扣券失败！！");
        return resultBean;
    }

    @Override
    public void updateCouponById(int orderId) {
        String orderkey = StringUtil.getRedisKey(RedisConstant.ORDER_USER, "" + orderId);
        Object o = redisTemplate.opsForValue().get(orderId);
        if (o!=null){
            TOrder order = (TOrder) o;
            int userid = order.getoUserid();
            //获取购买的商品集合
            String cartkey = StringUtil.getRedisKey(RedisConstant.USER_CART_PAY, "" + userid);
            Object o1 = redisTemplate.opsForValue().get(cartkey);
            List<TProductCartDTO> list = (List<TProductCartDTO>) o1;
            //获得商品总价格
            BigDecimal totalPrice = new BigDecimal(String.valueOf(0));
            for (TProductCartDTO product : list) {
                totalPrice = totalPrice.add(new BigDecimal(String.valueOf(product.getDanPrice())));
            }
            //保留两位小数
            Double talPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            //获得商品实际所用金额
            Double aDouble = order.getoPaycount();

            //该消费券的折扣的金额
            Double getDiscountAmount = 0.00;

            //获得用户用的优惠券及红包集合
            List<TUserCoupon> userCoupons = tUserCouponMapper.queryUserCouponByUserId(userid);

            //判断是否用了优惠券
            if(order.getoCoupon()!= 0){
                TCoupon tCoupon = tCouponMapper.selectByPrimaryKey(order.getoCoupon());
                //获得该消费券的折扣的金额
                getDiscountAmount = tCoupon.getDiscountAmount();

                //删除用户的该条优惠券数据
                for (TUserCoupon userCoupon : userCoupons) {
                    if (userCoupon.getCouponId()==order.getoCoupon()){
                        userCoupons.remove(userCoupon);
                    }
                }
            }

            //判断是否用了红包
            if(order.getoRedpacket()!=0){
                TCoupon redPacket = tCouponMapper.selectByPrimaryKey(order.getoRedpacket());
                //获得红包的金额大小
                Double discountAmount = redPacket.getDiscountAmount();
                if((talPrice-getDiscountAmount) >= discountAmount){
                    //红包金额全部用完
                    //删除用户的该条红包数据
                    for (TUserCoupon userCoupon : userCoupons) {
                        if (userCoupon.getCouponId()==order.getoRedpacket()){
                            userCoupons.remove(userCoupon);
                        }
                    }
                }else{
                    //红包的剩余金额为
                    discountAmount = discountAmount-(talPrice-getDiscountAmount);
                    //更新用户的该条红包数据
                    for (TUserCoupon userCoupon : userCoupons) {
                        if (userCoupon.getCouponId()==order.getoRedpacket()){
                            userCoupon.setRedpacketSurplus(discountAmount);
                        }
                    }
                }
            }

            //更新用户的所以优惠券及红包数据
            for (TUserCoupon userCoupon : userCoupons) {
               tUserCouponMapper.updateByPrimaryKeySelective(userCoupon);
            }

        }
    }
}
