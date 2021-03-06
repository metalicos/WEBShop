package com.ostap.komplikevych.webshop.entity;

import java.time.LocalDateTime;

public class Account extends AbstractEntity {

    private String email;
    private String password;
    private LocalDateTime createTime;
    private int roleId;
    private int shoppingCartId;
    private int accountStatusId;

    public String getAccountStatus(){
        return AccountStatus.getAccountStatus(accountStatusId).name();
    }

    public String getRole(){
        return Role.getRole(roleId).name();
    }

    public int getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(int accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", roleId=" + roleId +
                ", shoppingCartId=" + shoppingCartId +
                ", accountStatusId=" + accountStatusId +
                '}';
    }
}
