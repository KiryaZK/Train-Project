package com.aston.frontendpracticeservice.exception;

public class UserNotFoundException extends RuntimeException {

    public static final String USER_NOT_FOUND_MESSAGE = "User not found";

    public static final String USERS_NOT_FOUND_MESSAGE = "Users not found";

    public UserNotFoundException() {
        super(USER_NOT_FOUND_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
