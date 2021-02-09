package com.ostap.komplikevych.webshop.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
