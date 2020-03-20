package com.qf.service.impl;

import com.qf.constant.RedisConstant;
import com.qf.dto.TProductCartDTO;
import com.qf.entity.TOrder;
import com.qf.entity.TOrderDetail;
import com.qf.mapper.TOrderDetailMapper;
import com.qf.service.OrderDetailService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private TOrderDetailMapper tOrderDetailMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tOrderDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TOrderDetail record) {
        return tOrderDetailMapper.insert(record);
    }

    @Override
    public int insertSelective(TOrderDetail record) {
        return tOrderDetailMapper.insertSelective(record);
    }

    @Override
    public TOrderDetail selectByPrimaryKey(Integer id) {
        return tOrderDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TOrderDetail record) {
        return tOrderDetailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TOrderDetail record) {
        return tOrderDetailMapper.updateByPrimaryKey(record);
    }

    @Override
    public void creatOrderDetail(int orderId) {
        //获得订单对象拿userid
        String orderkey = StringUtil.getRedisKey(RedisConstant.ORDER_USER, "" + orderId);
        Object o = redisTemplate.opsForValue().get(orderId);
        if (o!=null){
            TOrder order = (TOrder) o;
            int userid = order.getoUserid();
            //获取购买的商品集合
            String cartkey = StringUtil.getRedisKey(RedisConstant.USER_CART_PAY, "" + userid);
            Object o1 = redisTemplate.opsForValue().get(cartkey);
            List<TProductCartDTO> list = (List<TProductCartDTO>) o1;

            TOrderDetail orderDetail = new TOrderDetail();
            //遍历集合，得到购买的商品信息，及数量
            for (TProductCartDTO tProductCartDTO : list) {
                orderDetail.setGoodsDate(tProductCartDTO.getProduct().getCreateTime());
                orderDetail.setoOrderid(orderId);
                orderDetail.setGoodsid(Math.toIntExact(tProductCartDTO.getProduct().getPid()));
                orderDetail.setGoodsname(tProductCartDTO.getProduct().getPname());
                orderDetail.setGoodsDescription("描述需要根据pid去数据库拿");
                orderDetail.setGoodsnum(tProductCartDTO.getCount());
                orderDetail.setGoodsprice(Double.valueOf(tProductCartDTO.getProduct().getPrice()));
                orderDetail.setGoodsTotalPrice(tProductCartDTO.getDanPrice());
                orderDetail.setGoodspic(tProductCartDTO.getProduct().getPimage());
                //往数据库添加订单详情
                tOrderDetailMapper.insertSelective(orderDetail);
            }

        }
    }
}
