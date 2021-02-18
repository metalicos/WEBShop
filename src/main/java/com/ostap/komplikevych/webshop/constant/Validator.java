package com.ostap.komplikevych.webshop.constant;

public class Validator {
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}";
    public static final String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    public static final String NAME_SURNAME_PATRONYMIC_EN = "[A-Z][a-zA-Z]{2,100}";
    public static final String NAME_SURNAME_PATRONYMIC_UA = "[А-ЯЁ][а-яёА-ЯЁ]{2,100}";

    public static boolean checkIfNullOrEmptyReturnTrue(String... var) {
        for (int i = 0; i < var.length; i++) {
            if (var[i] == null || var[i].isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfMatchValidator(String validator, String... var) {
        for (int i = 0; i < var.length; i++) {
            if (!var[i].matches(validator)) {
                return false;
            }
        }
        return true;
    }
}
