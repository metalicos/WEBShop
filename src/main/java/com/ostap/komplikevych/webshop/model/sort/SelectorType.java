package com.ostap.komplikevych.webshop.model.sort;

public enum SelectorType {
    ALL("all"),
    SEARCH_BAR("search-bar"),
    CATEGORY("category"),
    COLOR("color"),
    SIZE("size"),
    PRICE("price");

    String type;

    SelectorType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static SelectorType getSelectorType(String selector) {
        if (selector != null) {
            for (SelectorType t : SelectorType.values()) {
                if (t.type.equals(selector)) {
                    return t;
                }
            }
        }
        return SelectorType.ALL;
    }
}
