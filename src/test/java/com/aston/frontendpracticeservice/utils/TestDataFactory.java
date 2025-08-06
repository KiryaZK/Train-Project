package com.aston.frontendpracticeservice.utils;

import com.aston.frontendpracticeservice.domain.entity.Requisites;
import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.dto.requisites.RequisitesDTO;
import com.aston.frontendpracticeservice.dto.user.UserRequest;
import com.aston.frontendpracticeservice.dto.user.UserResponse;

import java.time.LocalDate;
import java.util.UUID;

import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.ACCOUNT_NUMBER;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.BIC;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.BIRTH_DATE;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.CORRESPONDENT_ACCOUNT;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.FIRST_NAME;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.KBK;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.KPP;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.LAST_NAME;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.LOGIN;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.NUMBER_INN;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.PASSPORT_NUMBER;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.PASSWORD;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.ROLES;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.SNILS;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.USER_ID;
import static com.aston.frontendpracticeservice.utils.constants.ValueNotExistDb.ACCOUNT_NUMBER_NOT_EXIST;
import static com.aston.frontendpracticeservice.utils.constants.ValueNotExistDb.LOGIN_NOT_EXIST;
import static com.aston.frontendpracticeservice.utils.constants.ValueNotExistDb.NUMBER_INN_NOT_EXIST;
import static com.aston.frontendpracticeservice.utils.constants.ValueNotExistDb.PASSPORT_NUMBER_NOT_EXIST;
import static com.aston.frontendpracticeservice.utils.constants.ValueNotExistDb.PASSWORD_NOT_EXIST;
import static com.aston.frontendpracticeservice.utils.constants.ValueNotExistDb.SNILS_NOT_EXIST;
import static com.aston.frontendpracticeservice.utils.constants.ValueNotExistDb.USER_ID_NOT_EXIST;

public final class TestDataFactory {

    private TestDataFactory() {}

    public static User getUser(boolean isExists) {
        var user = User.builder()
                .id(UUID.fromString(isExists ? USER_ID : USER_ID_NOT_EXIST))
                .firstname(FIRST_NAME)
                .lastname(LAST_NAME)
                .birthDate(LocalDate.parse(BIRTH_DATE))
                .snils(isExists ? SNILS : SNILS_NOT_EXIST)
                .passportNumber(isExists ? PASSPORT_NUMBER : PASSPORT_NUMBER_NOT_EXIST)
                .roles(ROLES)
                .login(isExists ? LOGIN : LOGIN_NOT_EXIST)
                .password(PasswordEncoderUtil
                        .encodePassword(isExists ? PASSWORD : PASSWORD_NOT_EXIST))
                .build();

        user.setRequisites(getRequisites(user, isExists));

        return user;
    }

    public static Requisites getRequisites(User user, boolean isExists) {
        return Requisites.builder()
                .id(user.getId())
                .user(user)
                .accountNumber(isExists ? ACCOUNT_NUMBER : ACCOUNT_NUMBER_NOT_EXIST)
                .bic(BIC)
                .correspondentAccount(CORRESPONDENT_ACCOUNT)
                .numberInn(isExists ? NUMBER_INN : NUMBER_INN_NOT_EXIST)
                .kpp(KPP)
                .kbk(KBK)
                .build();
    }

    public static UserRequest getUserRequest(boolean isExists) {
        return new UserRequest(
                FIRST_NAME,
                LAST_NAME,
                LocalDate.parse(BIRTH_DATE),
                isExists ? SNILS : SNILS_NOT_EXIST,
                isExists ? PASSPORT_NUMBER : PASSPORT_NUMBER_NOT_EXIST,
                isExists ? LOGIN : LOGIN_NOT_EXIST,
                isExists ? PASSWORD : PASSWORD_NOT_EXIST,
                getRequisitesDTO(isExists)
        );
    }

    public static RequisitesDTO getRequisitesDTO(boolean isExists) {
        return new RequisitesDTO(
                isExists ? ACCOUNT_NUMBER : ACCOUNT_NUMBER_NOT_EXIST,
                isExists ? NUMBER_INN : NUMBER_INN_NOT_EXIST,
                KBK
        );
    }

    public static UserResponse getUserResponse() {
        return new UserResponse(
                FIRST_NAME,
                LAST_NAME,
                LocalDate.parse(BIRTH_DATE),
                SNILS,
                PASSPORT_NUMBER,
                ROLES
        );
    }
}
