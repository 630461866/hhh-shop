package com.qf.service.impl;

import com.google.gson.Gson;
import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.TOrder;
import com.qf.mapper.TOrderMapper;
import com.qf.service.OrderService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private TOrderMapper tOrderMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TOrder record) {
        return tOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(TOrder record) {
        return tOrderMapper.insertSelective(record);
    }

    @Override
    public TOrder selectByPrimaryKey(Integer id) {
        return tOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TOrder record) {
        return tOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TOrder record) {
        return tOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    public ResultBean addOrder(String userId) {
        //获得订单号
        Calendar calendar = Calendar.getInstance();
        int year =calendar.get(Calendar.YEAR);
        StringBuffer  stringBuffer = new  StringBuffer();
        stringBuffer.append(year);
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int num=random.nextInt(10);
            stringBuffer.append(num);
        }
        int orderid = Integer.parseInt(stringBuffer.toString());

        ResultBean resultBean = new ResultBean();
        String redisKey = StringUtil.getRedisKey(RedisConstant.ORDER_USER, userId);
        Object o = redisTemplate.opsForValue().get(redisKey);
        if (o!=null){
            String orderjson = (String) o;
            Gson gson = new Gson();
            TOrder order = gson.fromJson(orderjson, TOrder.class);
            order.setId(orderid);
            int i = tOrderMapper.insertSelective(order);
            if (i>0){
                String orderKey = StringUtil.getRedisKey(RedisConstant.ORDER_USER, ""+orderid);
                redisTemplate.opsForValue().set(orderKey,gson.toJson(order)); //将order存入redis，pay服务使用
                redisTemplate.delete(redisKey);  //删除redis中的订单记录
                resultBean.setErrno(orderid);
                resultBean.setMessage("创建订单成功");
                return resultBean;
            }


        }
        resultBean.setErrno(0);
        resultBean.setMessage("创建订单失败！！");
        return resultBean;
    }

    @Override
    public ResultBean updateOrderById(int orderId) {
        ResultBean resultBean = new ResultBean();
        TOrder order = tOrderMapper.selectByPrimaryKey(orderId);
        if (order!=null){
            order.setoStatus(2);//更改订单状态
            int i = tOrderMapper.updateByPrimaryKeySelective(order);
            if (i>0){
                resultBean.setErrno(1);
                resultBean.setMessage("更新订单状态成功！！");
                return resultBean;
            }
        }
        resultBean.setErrno(0);
        resultBean.setMessage("更新订单状态失败！");
        return resultBean;
    }
}
