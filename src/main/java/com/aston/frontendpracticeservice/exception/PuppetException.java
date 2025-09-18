package com.aston.frontendpracticeservice.exception;

/**
 * Общий класс всех ошибок
 */
public class PuppetException extends RuntimeException {

    public PuppetException(String message) {
        super(message);
    }
}
