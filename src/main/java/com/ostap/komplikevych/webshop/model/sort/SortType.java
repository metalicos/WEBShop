package com.ostap.komplikevych.webshop.model.sort;

public enum SortType {
    NAME_A_Z("name_a_z"),
    NAME_Z_A("name_z_a"),
    PRICE_UP("price_up"),
    PRICE_DOWN("price_down"),
    DATE_NEW("date_new"),
    DATE_OLD("date_old");

    String type;

    SortType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
