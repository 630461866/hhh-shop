package com.qf.dto;

import com.qf.entity.TProduct;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author tancheng
 * @Date 2020/3/18
 */
public class TProductCartDTO implements Serializable{
    private TProduct product;

    private int count;

    private Date updateTime;

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
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
	 //得到单个商品的总价
    public double getDanPrice(){
        //得到单价
        BigDecimal priceOff = new BigDecimal(String.valueOf(this.product.getPrice()));
        //得到数量
        BigDecimal count = new BigDecimal(String.valueOf(this.getCount()));
        //得到单个商品总价
        BigDecimal danPrice = priceOff.multiply(count);

        return danPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
