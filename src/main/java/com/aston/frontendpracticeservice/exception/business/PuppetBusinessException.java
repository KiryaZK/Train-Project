package com.aston.frontendpracticeservice.exception.business;

import com.aston.frontendpracticeservice.exception.PuppetException;

/**
 * Для бизнес ошибок
 */
public class PuppetBusinessException extends PuppetException {

    public PuppetBusinessException(String message) {
        super(message);
    }
}
