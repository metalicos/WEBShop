package com.ostap.komplikevych.webshop.entity;

public class ShoppingCartHasProduct {
    int shoppingCartId;
    int productId;

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ShoppingCartHasProduct{" +
                "shoppingCartId=" + shoppingCartId +
                ", productId=" + productId +
                '}';
    }
}
