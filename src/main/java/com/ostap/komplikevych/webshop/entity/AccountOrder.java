package com.ostap.komplikevych.webshop.entity;

import java.time.LocalDateTime;

public class AccountOrder extends AbstractEntity {

    private LocalDateTime createTime;
    private long accountId;
    private long statusId;
    private long droppedByAccountId;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public long getDroppedByAccountId() {
        return droppedByAccountId;
    }

    public void setDroppedByAccountId(long droppedByAccountId) {
        this.droppedByAccountId = droppedByAccountId;
    }

    @Override
    public String toString() {
        return "AccountOrder{" +
                "createTime=" + createTime +
                ", accountId=" + accountId +
                ", statusId=" + statusId +
                ", droppedByAccountId=" + droppedByAccountId +
                '}';
    }
}
