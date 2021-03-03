package com.ostap.komplikevych.webshop.constant;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    public static final String UNSIGNED_DOUBLE = "([0-9]+.[0-9]+)|([0-9]+)";
    public static final String UNSIGNED_INTEGER = "[0-9]+";

    /**
     * Methode checks sentence for null and empty.
     * @param var
     * @return
     */
    public static boolean checkIfNullOrEmptyReturnTrue(String... var) {
        for (int i = 0; i < var.length; i++) {
            if (var[i] == null || var[i].isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Methode receive validator as RegEx and validates string.
     * @param validator
     * @param var
     * @return
     */
    public static boolean checkIfMatchValidator(String validator, @NotNull String... var) {
        for (int i = 0; i < var.length; i++) {
            if (!var[i].matches(validator)) {
                return false;

            }
        }
        return true;
    }

    /**
     * Methode check if sentence contains banned words/
     * @param var
     * @return
     */
    public static boolean checkIfContainsRestrictedWords(@NotNull String... var) {
        Const.logger.debug("Checking RESTRICTED Words");
        String restricted = Const.getProperty("restricted.words");
        Const.logger.debug("Restricted words: "+restricted);
        if (restricted != null) {
            List<String> wordsRestrictedList =
                    Arrays.stream(restricted.toLowerCase().split(" ")).collect(Collectors.toList());
            List<String> words;
            for (String s : var) {
                words = Arrays.stream(s.toLowerCase().split("\\s")).collect(Collectors.toList());
                for (String word : words) {
                    if (wordsRestrictedList.contains(word)) {
                        Const.logger.debug("Found restricted: "+word);
                        return true;
                    }
                }
            }
        }
        Const.logger.debug("Nothing found.");
        return false;
    }
}
