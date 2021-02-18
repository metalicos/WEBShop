package com.ostap.komplikevych.webshop.entity;

import com.ostap.komplikevych.webshop.constant.Const;

public enum Role {
    ADMIN(1),USER(2);

    private int id;
    Role(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role getRole(Account account){
        for(Role r:Role.values()){
            if(r.id==account.getRoleId()){
                return r;
            }
        }
        return Role.USER;
    }

}
