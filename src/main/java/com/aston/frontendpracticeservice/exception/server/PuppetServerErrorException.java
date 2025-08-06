package com.aston.frontendpracticeservice.exception.server;

import com.aston.frontendpracticeservice.exception.PuppetException;

/**
 * Для ошибок связанных с походом по сети
 */
public class PuppetServerErrorException extends PuppetException {

    public PuppetServerErrorException(String message) {
        super(message);
    }
}
