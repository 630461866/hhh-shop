package com.qf.service.impl;

import com.google.gson.Gson;
import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.PCA;
import com.qf.entity.TAddress;
import com.qf.mapper.PCAMapper;
import com.qf.mapper.TAddressMapper;
import com.qf.service.AddressServcie;
import com.qf.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServcieImpl implements AddressServcie{
    @Autowired
    private TAddressMapper tAddressMapper;
    @Autowired
    private PCAMapper pcaMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TAddress record) {
        return tAddressMapper.insert(record);
    }

    @Override
    public int insertSelective(TAddress record) {

        return tAddressMapper.insertSelective(record);
    }

    @Override
    public TAddress selectByPrimaryKey(Integer id) {
        return tAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TAddress record) {
        return tAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TAddress record) {
        return tAddressMapper.updateByPrimaryKey(record);
    }

    @Override
    public ResultBean queryAddressList(String userId) {
        int userIds = Integer.parseInt(userId);
        List<TAddress> tAddresseslist = tAddressMapper.queryAddressList(userIds);
        ResultBean resultBean = new ResultBean();
        if (tAddresseslist.size()>0){
            resultBean.setData(tAddresseslist);
            resultBean.setMessage("获取成功");
            return resultBean;
        }
        resultBean.setErrno(00001);
        resultBean.setMessage("获取地址失败，用户没有收货地址");
        return resultBean;
    }

    @Override
    public ResultBean insertAddressByUserId(int userId) {
        String redisKey = StringUtil.getRedisKey(RedisConstant.ADDRESS_USERID, "" + userId);
        Object o = redisTemplate.opsForValue().get(redisKey);
        String addressJson = (String) o;
        Gson gson = new Gson();
        TAddress tAddress = gson.fromJson(addressJson, TAddress.class);
        if ("是".equals(tAddress.getIsdefault())){
            List<TAddress> addressList = tAddressMapper.queryAddressList(tAddress.getUserid());
            for (TAddress address : addressList) {
                if ("是".equals(address.getIsdefault())){
                    address.setIsdefault("否");
                    int i = tAddressMapper.updateByPrimaryKeySelective(address);
                    if (i>0){
                        System.out.println("更新原默认地址为普通地址成功！");
                    }
                }
            }
        }
        int province = Integer.parseInt(tAddress.getProvince());
        PCA pro = pcaMapper.selectByPrimaryKey(province);
        tAddress.setProvince(pro.getDistrictName());

        int city = Integer.parseInt(tAddress.getCity());
        PCA ci = pcaMapper.selectByPrimaryKey(city);
        tAddress.setCity(ci.getDistrictName());

        int area = Integer.parseInt(tAddress.getArea());
        PCA ar = pcaMapper.selectByPrimaryKey(area);
        tAddress.setArea(ar.getDistrictName());
        System.out.println(tAddress);
        int count = 1;
        int i = tAddressMapper.insertSelective(tAddress);
        System.out.println("添加次数："+count);
        count = count+1;
        ResultBean resultBean = new ResultBean();
        if (i>0){
            redisTemplate.delete(redisKey);
            resultBean.setMessage("存入地址成功！！");
            return resultBean;
        }
        resultBean.setErrno(0);
        resultBean.setMessage("存入地址失败！！");
        return resultBean;
    }

    @Override
    public ResultBean updateAddressDefault(int addressId) {
        ResultBean resultBean = new ResultBean();

        TAddress tAddress = tAddressMapper.selectByPrimaryKey(addressId);
        tAddress.setIsdefault("是");
        int userId = tAddress.getUserid();

        List<TAddress> addresseslist = tAddressMapper.queryAddressList(userId);
        for (TAddress address : addresseslist) {
            if ("是".equals(address.getIsdefault())){
                address.setIsdefault("否");
                int i = tAddressMapper.updateByPrimaryKeySelective(address);
                if (i>0){
                    System.out.println("更新原默认地址为普通地址成功！");
                }else {
                    resultBean.setMessage("更新原默认地址为普通地址失败！");
                    resultBean.setErrno(0);
                    return resultBean;
                }
            }
        }

        int i = tAddressMapper.updateByPrimaryKeySelective(tAddress);
        if (i>0){
            resultBean.setErrno(1);
            return resultBean;
        }
        resultBean.setErrno(0);
        return resultBean;
    }
}
