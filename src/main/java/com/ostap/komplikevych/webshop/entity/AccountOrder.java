package com.ostap.komplikevych.webshop.entity;

import java.time.LocalDateTime;
import java.util.List;

public class AccountOrder extends AbstractEntity {

    private LocalDateTime createTime;
    private int accountId;
    private int statusId;
    private int droppedByAccountId;
    private List<ProductInOrder> products;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getDroppedByAccountId() {
        return droppedByAccountId;
    }

    public void setDroppedByAccountId(int droppedByAccountId) {
        this.droppedByAccountId = droppedByAccountId;
    }

    public List<ProductInOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInOrder> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "AccountOrder{" + "\n" +
                "createTime=" + createTime + "\n" +
                ", accountId=" + accountId + "\n" +
                ", statusId=" + statusId + "\n" +
                ", droppedByAccountId=" + droppedByAccountId + "\n" +
                ", products=" + products + "\n" +
                '}' + "\n";
    }
}
