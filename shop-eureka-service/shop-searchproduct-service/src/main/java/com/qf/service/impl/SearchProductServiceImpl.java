package com.qf.service.impl;

import com.google.gson.Gson;
import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.dto.TProductCartDTO;
import com.qf.entity.TOrder;
import com.qf.entity.TProduct;
import com.qf.mapper.TProductMapper;
import com.qf.service.SearchProductService;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SearchProductServiceImpl implements SearchProductService{
    @Autowired
    TProductMapper tProductMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    public ResultBean selectByPrimaryKey(Long id){
        ResultBean resultBean = new ResultBean();
        TProduct tProduct = tProductMapper.selectByPrimaryKey(id);
        if (tProduct!=null){
            Gson gson = new Gson();
            resultBean.setData(gson.toJson(tProduct));
            return resultBean;
        }
        resultBean.setErrno(0);
        resultBean.setMessage("获取产品失败！！");
        return resultBean;
    }

    @Override
    public void updateProductCount(int orderId) {
        String orderkey = StringUtil.getRedisKey(RedisConstant.ORDER_USER, "" + orderId);
        Object o = redisTemplate.opsForValue().get(orderId);
        if (o!=null){
            TOrder order = (TOrder) o;
            int userid = order.getoUserid();
            //获取购买的商品集合
            String cartkey = StringUtil.getRedisKey(RedisConstant.USER_CART_PAY, "" + userid);
            Object o1 = redisTemplate.opsForValue().get(cartkey);
            List<TProductCartDTO> list = (List<TProductCartDTO>) o1;
            //遍历集合，得到商品id，及数量

            for (TProductCartDTO tProductCartDTO : list) {
                Long productId = tProductCartDTO.getProduct().getPid();
                int productNum = tProductCartDTO.getCount();
                TProduct product = tProductMapper.selectByPrimaryKey(productId);
                Integer stock = product.getStock();
                stock = stock-productNum;
                product.setStock(stock);
                product.setUpdateTime(new Date());
                product.setUpdateUser((long) userid);
                int i = tProductMapper.updateByPrimaryKeySelective(product);
                if (i>0){
                    System.out.println("更新库存成功！");
                }
            }
        }



    }

}
