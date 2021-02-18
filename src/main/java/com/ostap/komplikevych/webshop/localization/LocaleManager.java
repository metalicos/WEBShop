package com.ostap.komplikevych.webshop.localization;

import com.ostap.komplikevych.webshop.constant.Const;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManager {
    public static String translate(Resource res,Language lang){
        ResourceBundle resources = ResourceBundle.getBundle("resources", new Locale(lang.getName()));
        String translate =  resources.getString(res.getName());
        Const.logger.trace("Getting resource ("+res.getName()+") translation: "+translate);
        return translate;
    }
}
