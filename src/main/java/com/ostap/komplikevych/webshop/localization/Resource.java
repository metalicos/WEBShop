package com.ostap.komplikevych.webshop.localization;

public enum Resource {
    /*  LOGIN PAGE RESOURCES */
    LOGIN_("login.greeting");

    private final String name;
    Resource(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
