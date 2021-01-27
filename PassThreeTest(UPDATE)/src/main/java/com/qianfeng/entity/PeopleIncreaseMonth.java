package com.qianfeng.entity;

import java.util.List;

public class PeopleIncreaseMonth {
    private Object data;
    private Object categories;

    public PeopleIncreaseMonth() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getCategories() {
        return categories;
    }

    public void setCategories(Object categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "PeopleIncreaseMonth{" +
                "data=" + data +
                ", categories=" + categories +
                '}';
    }
}
