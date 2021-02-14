package com.ostap.komplikevych.webshop.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductInOrder {
    private Product product;
    private BigDecimal price;
    private int productAmount;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;

    public ProductInOrder() {
    }

    public ProductInOrder(Product product, BigDecimal price, int productAmount, LocalDateTime createTime, LocalDateTime lastUpdateTime) {
        this.product = product;
        this.price = price;
        this.productAmount = productAmount;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "\n{Product_ID=" + product.getId() + ", Product_Amount=" + productAmount +
                ", Price=" + price + ", CreateTime=" + createTime + ", LastUpdateTime=" + lastUpdateTime + "}\n";
    }
}
