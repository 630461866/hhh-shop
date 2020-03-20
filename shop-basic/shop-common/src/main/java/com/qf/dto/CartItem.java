package com.qf.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author tancheng
 * @Date 2020/3/18
 */
public class CartItem implements Serializable{
    private Long productId;
    private int count;
    private Date updateTime;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
