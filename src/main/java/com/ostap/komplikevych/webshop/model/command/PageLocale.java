package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.localization.Language;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class PageLocale {
    public Language getLanguage(HttpServletRequest request) {
        String lang = (String) request.getAttribute(SessionAttribute.LANGUAGE);
        Language language = Language.getLang(lang);
        Locale locale = new Locale(language.getName(), "UK");

        ResourceBundle file = ResourceBundle.getBundle("", locale);


        return language;
    }
}
