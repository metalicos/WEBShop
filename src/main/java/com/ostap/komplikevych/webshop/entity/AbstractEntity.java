package com.ostap.komplikevych.webshop.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
