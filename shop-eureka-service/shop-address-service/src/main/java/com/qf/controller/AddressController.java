package com.qf.controller;

import com.qf.dto.ResultBean;
import com.qf.entity.PCA;
import com.qf.entity.TAddress;
import com.qf.service.AddressServcie;
import com.qf.service.PcaService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressServcie addressServcie;
    @Autowired
    private PcaService pcaService;

    @RequestMapping("queryaddress/{userId}")
    @ResponseBody
    public ResultBean queryAddressList(@PathVariable String userId){
        ResultBean resultBean = addressServcie.queryAddressList(userId);
        return resultBean;
    }
    @RequestMapping("queryprovince")
    @ResponseBody
    public ResultBean queryProList(){
        return pcaService.queryProList();
    }

    @RequestMapping("getCityList/{id}")
    @ResponseBody
    public ResultBean getCityListByProName(@PathVariable String id){
       return pcaService.getCityListByProName(id);
    }

    @RequestMapping("getAreaList/{id}")
    @ResponseBody
    public ResultBean getAreaListByProName(@PathVariable String id){
        ResultBean resultBean = pcaService.getAreaListByProName(id);
        return resultBean;
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public ResultBean deleteByPrimaryKey(@PathVariable String id){
        int addressId = Integer.parseInt(id);
        int i = addressServcie.deleteByPrimaryKey(addressId);
        ResultBean resultBean = new ResultBean();
        if (i>0){
            resultBean.setErrno(2);
            return resultBean;
        }
        resultBean.setErrno(0);
        return resultBean;
    }
    @RequestMapping("add/{userId}")
    @ResponseBody
    public ResultBean addinsert(@PathVariable String userId){
        int uId = Integer.parseInt(userId);
        return addressServcie.insertAddressByUserId(uId);
    }

    @RequestMapping("query/{id}")
    @ResponseBody
    public ResultBean queryByPrimaryKey(@PathVariable String id){
        return pcaService.selectByPrimaryKey(id);
    }
    @RequestMapping("edit")
    public void editAddress(TAddress record){

    }
    @RequestMapping("setdefault/{id}")
    @ResponseBody
    public ResultBean updateAddressDefault(@PathVariable String id){
        int addressId = Integer.parseInt(id);
       return addressServcie.updateAddressDefault(addressId);
    }

}
