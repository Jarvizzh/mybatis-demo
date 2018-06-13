package com.mybatis.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by ZJH on 2017/7/29.
 */
public class Order implements Serializable {

    private int orderId;
    private String orderIdentity;
    private User user;
    private Set<OrderDetail> orderDetails;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderIdentity() {
        return orderIdentity;
    }

    public void setOrderIdentity(String orderIdentity) {
        this.orderIdentity = orderIdentity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderIdentity='" + orderIdentity + '\'' +
                ", user=" + user +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
