package com.qf.service;

import com.qf.dto.ResultBean;
import com.qf.entity.TAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@FeignClient(value = "shop-address-service")
public interface AddressService {

    @RequestMapping("address/queryaddress/{userId}")
    public ResultBean queryAddressList(@PathVariable("userId") String userId);

    @RequestMapping("address/queryprovince")
    public ResultBean queryProList();

    @RequestMapping("address/getCityList/{id}")
    ResultBean getCityListByProName(@PathVariable("id") String id);

    @RequestMapping("address/getAreaList/{id}")
    ResultBean getAreaListByProName(@PathVariable("id") String id);

    @RequestMapping("address/query/{id}")
    public ResultBean queryByPrimaryKey(@PathVariable("id") String id);

    @RequestMapping("address/add/{userId}")
    public ResultBean addinsert(@PathVariable("userId") String userId);

    @RequestMapping("address/setdefault/{id}")
    public ResultBean updateAddressDefault(@PathVariable("id") String id);

    @RequestMapping("address/delete/{id}")
    public ResultBean deleteByPrimaryKey(@PathVariable("id") String id);
}
