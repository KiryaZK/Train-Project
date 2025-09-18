package com.aston.frontendpracticeservice.exception.business;

import static com.aston.frontendpracticeservice.utils.constants.ExceptionMessage.USER_NOT_FOUND_MESSAGE;

public class UserNotFoundException extends PuppetBusinessException  {

    public UserNotFoundException() {
        super(USER_NOT_FOUND_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
