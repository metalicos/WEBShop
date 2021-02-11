package com.ostap.komplikevych.webshop.entity;

import java.time.LocalDateTime;

public class ShoppingCart extends AbstractEntity {
    private LocalDateTime lastUpdate;

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "lastUpdate=" + lastUpdate +
                '}';
    }
}
