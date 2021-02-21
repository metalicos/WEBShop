package com.ostap.komplikevych.webshop.localization;

import com.ostap.komplikevych.webshop.entity.Role;

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

    public static Language getLang(String language){
        if("uk".equals(language))
            return Language.UA;
        for(Language l:Language.values()){
            if(l.name.equals(language)){
                return l;
            }
        }
        return Language.EN;
    }
}
