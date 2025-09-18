package com.aston.frontendpracticeservice.exception.auth;

import com.aston.frontendpracticeservice.exception.PuppetException;

/**
 * Для ошибок авторизации
 */
public class PuppetAuthException extends PuppetException {

    public PuppetAuthException(String message) {
        super(message);
    }
}
