package com.ostap.komplikevych.webshop.localization;

public enum Language {
    UA("ua"),
    EN("en");

    private final String name;
    Language(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
