package com.ostap.komplikevych.webshop.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ShoppingCart extends AbstractEntity {

    private LocalDateTime lastUpdate;
    private List<ProductInCart> products;

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<ProductInCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInCart> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        String prod = "empty";
        if (products != null) {
            prod = Arrays.toString(products.toArray());
        }
        return "ShoppingCart{\n" +
                "lastUpdate=" + lastUpdate + "\n" +
                "products=" + prod + "\n}";
    }
}
