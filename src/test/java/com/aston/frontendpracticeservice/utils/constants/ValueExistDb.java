package com.aston.frontendpracticeservice.utils.constants;

import com.aston.frontendpracticeservice.security.Role;

import java.util.HashSet;
import java.util.Set;

/**
 * Константы c существующими данными из тестовых SQL файлов
 */
public final class ValueExistDb {

    private ValueExistDb() {}

    public static final String USER_ID = "8e9774e8-1546-4d9d-ae97-e9c3978ec7e1";
    public static final String FIRST_NAME = "Иван";
    public static final String LAST_NAME = "Петров";
    public static final String BIRTH_DATE = "1990-05-15";
    public static final String SNILS = "12345678901";
    public static final String PASSPORT_NUMBER = "4512123456";
    public static final String LOGIN = "ivan_petrov";
    public static final String PASSWORD = "password1";
    public static final Set<Role> ROLES = new HashSet<>() {{
        add(Role.USER);
        add(Role.ADMIN);
    }};

    public static final String ACCOUNT_NUMBER = "64121755715063391863";
    public static final String BIC = "044525225";
    public static final String NUMBER_INN = "336610704726";
    public static final String KPP = "773601001";
    public static final String KBK = "31116152832730522370";
    public static final String CORRESPONDENT_ACCOUNT = "30101810700000000187";
}
