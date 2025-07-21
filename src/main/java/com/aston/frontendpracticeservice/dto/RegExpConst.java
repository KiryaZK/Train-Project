package com.aston.frontendpracticeservice.dto;

/**
 * Константы для регулярных выражений
 */
public final class RegExpConst {

    public static final String REGEXP_PASSPORT = "^\\d{10}$";
    public static final String REGEXP_INN = "^\\d{12}$";
    public static final String REGEXP_SNILS = "^\\d{11}$";
    public static final String REGEXP_ACCOUNT_SIZE = "^\\d{20}$";

    private RegExpConst() {}
}
