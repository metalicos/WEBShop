package com.ostap.komplikevych.webshop.localization;

public enum Language {
    UA("ua"),
    EN("en");

    private final String name;
    public static final Language DEFAULT_LANGUAGE = Language.EN;

    Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Language getLang(String language) {
        if (language != null) {
            if ("uk".equals(language))
                return Language.UA;
            for (Language l : Language.values()) {
                if (l.name.equals(language)) {
                    return l;
                }
            }
        }
        return DEFAULT_LANGUAGE;
    }
}
