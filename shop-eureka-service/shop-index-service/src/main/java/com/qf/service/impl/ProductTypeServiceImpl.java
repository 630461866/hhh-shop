package com.qf.service.impl;

import com.qf.constant.StringConstant;
import com.qf.entity.TProductType;
import com.qf.mapper.TProductTypeMapper;
import com.qf.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ProductTypeServiceImpl implements IProductTypeService {

    @Autowired
    private TProductTypeMapper productTypeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<TProductType> list() {
        //从redis拿
        String key = "productTypes";
        List<TProductType> types = (List<TProductType>) redisTemplate.opsForValue().get(key);

        if (types==null){

            //上分布式锁
            String uuid = UUID.randomUUID().toString();

            //原子操作 防止线程安全问题和事务问题
         Boolean  absent =  redisTemplate.opsForValue().setIfAbsent(StringConstant.redis_types_lock,uuid,5, TimeUnit.MINUTES);
            if (absent){

                try {
                    //查数据
                    types = productTypeMapper.list();
                    //存到Redis中
                    redisTemplate.opsForValue().set(key,types);

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //释放锁
                  String currentUUID = (String) redisTemplate.opsForValue().get(StringConstant.redis_types_lock);
                  if (uuid.equals(currentUUID)){
                      redisTemplate.delete(StringConstant.redis_types_lock);
                  }
                }
            }else {

                try {
                    Thread.sleep(10);
                    return list();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

        return types;
    }
}
