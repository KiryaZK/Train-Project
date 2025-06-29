package com.aston.frontendpracticeservice.dto;

/**
 * Константы для сообщения при валидации
 */
public final class ValidationMessageConst {

    public static final String NOT_BLANK_MESSAGE = "Поле не должно быть пустым";
    public static final String NOT_NULL_MESSAGE = "Поле обязательно к заполнению";
    public static final String PAST_MESSAGE = "Дата должна быть в прошлом";
    public static final String PASSPORT_SIZE_MESSAGE = "Серия и номер паспорта состоят из 10 цифр";
    public static final String INN_SIZE_MESSAGE = "ИНН состоит из 12 цифр";
    public static final String SNILS_SIZE_MESSAGE = "Длина снилс - 11 цифр";

    private ValidationMessageConst() {}
}
