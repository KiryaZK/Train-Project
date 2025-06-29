package com.aston.frontendpracticeservice.utils.constants;

/**
 * Константы с URL для тестов
 */
public final class UrlDescription {

    private UrlDescription() {}

    public static final String GET_ALL_USERS_URL = "/api/v1/puppet-lab/users";
    public static final String DELETE_USER_BY_ID = "/api/v1/puppet-lab/users/{id}";
    public static final String GET_USER_BY_ID = "/api/v1/puppet-lab/users/{id}";
    public static final String POST_CREATE_NEW_USER_URL = "/api/v1/puppet-lab/users";
    public static final String PUT_UPDATE_USER_URL = "/api/v1/puppet-lab/users/{id}";
    public static final String GET_USER_VIEW_URL = "/api/v1/puppet-lab/users/{id}/account-details";
}
