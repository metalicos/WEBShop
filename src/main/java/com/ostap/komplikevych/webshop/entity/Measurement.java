package com.ostap.komplikevych.webshop.entity;

public enum Measurement {
    MM("mm"),
    CM("cm"),
    M("m");

    String value;

    Measurement(String value) {
        this.value = value;
    }


    public static Measurement getMeasurement(String data) {
        for (Measurement m : Measurement.values()) {
            if (m.value.equals(data)) {
                return m;
            }
        }
        return Measurement.MM;
    }
}
