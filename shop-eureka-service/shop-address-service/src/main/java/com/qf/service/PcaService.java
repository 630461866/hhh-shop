package com.qf.service;
import com.qf.dto.ResultBean;
import com.qf.entity.PCA;


public interface PcaService {
    int deleteByPrimaryKey(Integer id);

    int insert(PCA record);

    int insertSelective(PCA record);

    ResultBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PCA record);

    int updateByPrimaryKey(PCA record);

    ResultBean queryProList();

    ResultBean getCityListByProName(String districtName);

    ResultBean getAreaListByProName(String districtName);
}
