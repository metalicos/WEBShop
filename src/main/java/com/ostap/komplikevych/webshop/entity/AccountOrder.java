package com.ostap.komplikevych.webshop.entity;

import com.ostap.komplikevych.webshop.dao.AccountDetailDao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AccountOrder extends AbstractEntity {

    private LocalDateTime createTime;
    private int accountId;
    private int statusId;
    private int droppedByAccountId;
    private BigDecimal totalOrderPrice;
    private int deliveryId;

    private List<ProductInOrder> products;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getNameOfOrderOwner() {
        AccountDetailDao dao = new AccountDetailDao();
        AccountDetail accountDetail = dao.readAccountDetailByAccountId(accountId);
        return accountDetail.getSurnameUa()+" "+accountDetail.getFirstNameUa()+" "+accountDetail.getPatronymicUa();
    }

    public String getFormattedCreateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return createTime.format(formatter);
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

    public BigDecimal getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(BigDecimal totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public List<ProductInOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInOrder> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "AccountOrder{" +
                "createTime=" + createTime +
                ", accountId=" + accountId +
                ", statusId=" + statusId +
                ", droppedByAccountId=" + droppedByAccountId +
                ", totalOrderPrice=" + totalOrderPrice +
                ", deliveryId=" + deliveryId +
                ", products=" + products +
                '}';
    }
}
