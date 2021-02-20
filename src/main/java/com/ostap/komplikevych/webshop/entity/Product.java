package com.ostap.komplikevych.webshop.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product extends AbstractEntity {
    private BigDecimal price;
    private int amount;
    private int orderedAmount;
    private int categoryId;
    private LocalDateTime createDate;

    public Product() {
    }

    public Product(BigDecimal price, int amount, int orderedAmount, int categoryId) {
        this.price = price;
        this.amount = amount;
        this.orderedAmount = orderedAmount;
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOrderedAmount() {
        return orderedAmount;
    }

    public void setOrderedAmount(int orderedAmount) {
        this.orderedAmount = orderedAmount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", amount=" + amount +
                ", orderedAmount=" + orderedAmount +
                ", categoryId=" + categoryId +
                ", createDate=" + createDate +
                '}';
    }
}
