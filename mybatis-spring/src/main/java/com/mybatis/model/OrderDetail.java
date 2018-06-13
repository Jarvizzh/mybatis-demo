package com.mybatis.model;

import java.io.Serializable;

/**
 * Created by ZJH on 2017/7/29.
 */
public class OrderDetail implements Serializable {

    private int orderDetailId;
    private int counts;
    private Item item;

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
