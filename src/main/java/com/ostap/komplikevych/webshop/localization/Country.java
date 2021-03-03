//package com.ostap.komplikevych.webshop.localization;
//
//public enum Country {
//    UKRAINE("ua"),
//    ENGLAND("en");
//
//    private final String name;
//    public static final Country DEFAULT_COUNTRY = Country.ENGLAND;
//
//    Language(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public static Language getLang(String language) {
//        if (language != null) {
//            if ("uk".equals(language))
//                return Language.UA;
//            for (Language l : Language.values()) {
//                if (l.name.equals(language)) {
//                    return l;
//                }
//            }
//        }
//        return DEFAULT_LANGUAGE;
//    }
//}
