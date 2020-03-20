package com.qf.service.impl;
import com.qf.dto.ResultBean;
import com.qf.entity.PCA;
import com.qf.mapper.PCAMapper;
import com.qf.service.PcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PcaServiceImpl implements PcaService {

    @Autowired
    private PCAMapper pcaMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return pcaMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PCA record) {
        return pcaMapper.insert(record);
    }

    @Override
    public int insertSelective(PCA record) {
        return pcaMapper.insertSelective(record);
    }

    @Override
    public ResultBean selectByPrimaryKey(String id) {
        int i =Integer.parseInt(id);
        ResultBean resultBean = new ResultBean();
        PCA pca = pcaMapper.selectByPrimaryKey(i);
        resultBean.setData(pca);
        return resultBean;
    }

    @Override
    public int updateByPrimaryKeySelective(PCA record) {
        return pcaMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PCA record) {
        return pcaMapper.updateByPrimaryKey(record);
    }

    @Override
    public ResultBean queryProList() {
        ResultBean resultBean = new ResultBean();
        List<PCA> list= pcaMapper.queryProList();
        if (list.size()>0){
            resultBean.setData(list);
            return resultBean;
        }
        resultBean.setErrno(0);
        resultBean.setMessage("获取失败！！");
        return resultBean;
    }

    @Override
    public ResultBean getCityListByProName(String id) {
        int i = Integer.parseInt(id);
        ResultBean resultBean = new ResultBean();
        List<PCA> list= pcaMapper.getListByProName(i);
        if (list.size()>0){
            resultBean.setData(list);
            return resultBean;
        }
        resultBean.setErrno(0);
        resultBean.setMessage("获取失败！！");
        return resultBean;
    }

    @Override
    public ResultBean getAreaListByProName(String id) {
        int i = Integer.parseInt(id);
        ResultBean resultBean = new ResultBean();
        List<PCA> list= pcaMapper.getListByProName(i);
        if (list.size()>0){
            resultBean.setData(list);
            return resultBean;
        }
        resultBean.setErrno(0);
        resultBean.setMessage("获取失败！！");
        return resultBean;
    }
}
