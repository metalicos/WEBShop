package com.ostap.komplikevych.webshop.entity;

import java.time.LocalDateTime;

public class Account extends AbstractEntity {

    private String email;
    private String password;
    private LocalDateTime createTime;
    private int roleId;
    private int shoppingCartId;

    public Account(String email, String password, int roleId) {
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public Account() {
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
                '}';
    }
}
