package com.ostap.komplikevych.webshop.constant;

import javax.validation.constraints.NotNull;

/**
 * Class that provides data check considering different type rules.
 *
 * @author Ostap Komplikevych
 */
public class Validator {
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}";
    public static final String EMAIL =
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String NAME_SURNAME_PATRONYMIC_EN =
            "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
    public static final String NAME_SURNAME_PATRONYMIC_UA =
            "(?i)(^[абвгґдеєжзиіїйклмнопрстуфхцчшщьюяыъэё])" +
                    "((?![ .,'-]$)[абвгґдеєжзиіїйклмнопрстуфхцчшщьюяыъэё .,'-]){0,24}$";

    public static boolean checkIfNullOrEmptyReturnTrue(String... var) {
        for (int i = 0; i < var.length; i++) {
            if (var[i] == null || var[i].isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfMatchValidator(String validator,@NotNull String... var) {
        for (int i = 0; i < var.length; i++) {
            if (!var[i].matches(validator)) {
                return false;
            }
        }
        return true;
    }
}
