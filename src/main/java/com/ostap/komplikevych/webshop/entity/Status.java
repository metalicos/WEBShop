package com.ostap.komplikevych.webshop.entity;

public enum Status {
    REGISTERED(1), PAID(2), CANCELED(3);

    int id;

    Status(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
