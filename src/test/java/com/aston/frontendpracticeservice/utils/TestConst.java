package com.aston.frontendpracticeservice.utils;

import com.aston.frontendpracticeservice.security.Role;

import java.util.HashSet;
import java.util.Set;

public final class TestConst {

    private TestConst() {}

    /**
     * Константы с примерными значениями для тестов
     */
    public static final String USER_ID = "8e9774e8-1546-4d9d-ae97-e9c3978ec7e1";
    public static final String INCORRECT_USER_ID = "5e9774e8-1546-4d9d-ae97-e9c3978ec7e0";
    public static final String FIRST_NAME = "Иван";
    public static final String LAST_NAME = "Петров";
    public static final String BIRTH_DATE = "1990-05-15";
    public static final String NUMBER_INN = "123456789012";
    public static final String SNILS = "12345678901";
    public static final String PASSPORT_NUMBER = "4512123456";
    public static final String LOGIN = "ivan_petrov";
    public static final String PASSWORD = "password1";
    public static final Set<Role> ROLES = new HashSet<>() {{
        add(Role.USER);
        add(Role.ADMIN);
    }};

    /**
     * Константы с URL для тестов
     */
    public static final String GET_ALL_USERS_URL = "/api/v1/puppet-lab/users";
    public static final String DELETE_USER_BY_ID = "/api/v1/puppet-lab/users/{id}";
    public static final String GET_USER_BY_ID = "/api/v1/puppet-lab/users/{id}";
    public static final String POST_CREATE_NEW_USER_URL = "/api/v1/puppet-lab/users";
    public static final String PUT_UPDATE_USER_URL = "/api/v1/puppet-lab/users/{id}";

    /**
     * Константы для Exception
     */
    public static final String USER_NOT_FOUND = "User not found";
}
