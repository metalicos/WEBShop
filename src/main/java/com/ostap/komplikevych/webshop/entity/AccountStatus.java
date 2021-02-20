package com.ostap.komplikevych.webshop.entity;

public enum AccountStatus {
    ENABLED(1), DISABLED(2);

    int id;

    AccountStatus(int id) {
        this.id = id;
    }

    public static AccountStatus getAccountStatus(Account account) {
        for (AccountStatus a : AccountStatus.values()) {
            if (a.id == account.getRoleId()) {
                return a;
            }
        }
        return AccountStatus.ENABLED;
    }

    public int getId() {
        return id;
    }
}
