package com.ostap.komplikevych.webshop.entity;

public class ProductInCart {
    private Product product;
    private int amount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "\n{Product_ID="+product.getId()+", Product_Amount=" + amount + "}\n";
    }
}
