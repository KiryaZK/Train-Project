package com.aston.frontendpracticeservice.utils;

import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.dto.user.UserRequest;
import com.aston.frontendpracticeservice.dto.user.UserResponse;

import java.time.LocalDate;
import java.util.UUID;

import static com.aston.frontendpracticeservice.utils.TestConst.BIRTH_DATE;
import static com.aston.frontendpracticeservice.utils.TestConst.FIRST_NAME;
import static com.aston.frontendpracticeservice.utils.TestConst.LAST_NAME;
import static com.aston.frontendpracticeservice.utils.TestConst.LOGIN;
import static com.aston.frontendpracticeservice.utils.TestConst.NUMBER_INN;
import static com.aston.frontendpracticeservice.utils.TestConst.PASSPORT_NUMBER;
import static com.aston.frontendpracticeservice.utils.TestConst.PASSWORD;
import static com.aston.frontendpracticeservice.utils.TestConst.ROLES;
import static com.aston.frontendpracticeservice.utils.TestConst.SNILS;
import static com.aston.frontendpracticeservice.utils.TestConst.USER_ID;

public final class TestDataFactory {

    private TestDataFactory() {}

    public static User getUser() {
        return User.builder()
                .id(UUID.fromString(USER_ID))
                .firstname(FIRST_NAME)
                .lastname(LAST_NAME)
                .birthDate(LocalDate.parse(BIRTH_DATE))
                .numberInn(NUMBER_INN)
                .snils(SNILS)
                .passportNumber(PASSPORT_NUMBER)
                .roles(ROLES)
                .login(LOGIN)
                .password(PasswordEncoderUtil.encodePassword(PASSWORD))
                .build();
    }

    public static UserRequest getUserRequest() {
        return new UserRequest(
                FIRST_NAME,
                LAST_NAME,
                LocalDate.parse(BIRTH_DATE),
                NUMBER_INN,
                SNILS,
                PASSPORT_NUMBER,
                LOGIN,
                PASSWORD
        );
    }

    public static UserResponse getUserResponse() {
        return new UserResponse(
                FIRST_NAME,
                LAST_NAME,
                LocalDate.parse(BIRTH_DATE),
                NUMBER_INN,
                SNILS,
                PASSPORT_NUMBER,
                ROLES
        );
    }

}
