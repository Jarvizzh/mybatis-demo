package com.mybatis.model;

import java.io.Serializable;

/**
 * Created by ZJH on 2017/8/2.
 */
public class Item implements Serializable {

    private Integer itemId;
    private String itemName;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
