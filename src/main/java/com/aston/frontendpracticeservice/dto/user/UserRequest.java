package com.aston.frontendpracticeservice.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

import static com.aston.frontendpracticeservice.utils.DescriptionConst.DESCRIPTION_BIRTH_DATE;
import static com.aston.frontendpracticeservice.utils.DescriptionConst.DESCRIPTION_FIRSTNAME;
import static com.aston.frontendpracticeservice.utils.DescriptionConst.DESCRIPTION_LASTNAME;
import static com.aston.frontendpracticeservice.utils.DescriptionConst.DESCRIPTION_NUMBER_INN;
import static com.aston.frontendpracticeservice.utils.DescriptionConst.DESCRIPTION_PASSPORT_NUMBER;
import static com.aston.frontendpracticeservice.utils.DescriptionConst.DESCRIPTION_PASSWORD;
import static com.aston.frontendpracticeservice.utils.DescriptionConst.DESCRIPTION_ROLE;
import static com.aston.frontendpracticeservice.utils.DescriptionConst.DESCRIPTION_SNILS;
import static com.aston.frontendpracticeservice.utils.ValuesConst.EXAMPLE_BIRTH_DATE;
import static com.aston.frontendpracticeservice.utils.ValuesConst.EXAMPLE_FIRSTNAME;
import static com.aston.frontendpracticeservice.utils.ValuesConst.EXAMPLE_LASTNAME;
import static com.aston.frontendpracticeservice.utils.ValuesConst.EXAMPLE_LOGIN;
import static com.aston.frontendpracticeservice.utils.ValuesConst.EXAMPLE_NUMBER_INN;
import static com.aston.frontendpracticeservice.utils.ValuesConst.EXAMPLE_PASSPORT_NUMBER;
import static com.aston.frontendpracticeservice.utils.ValuesConst.EXAMPLE_PASSWORD;
import static com.aston.frontendpracticeservice.utils.ValuesConst.EXAMPLE_SNILS;
import static com.aston.frontendpracticeservice.dto.ValidationMessageConst.INN_SIZE_MESSAGE;
import static com.aston.frontendpracticeservice.dto.ValidationMessageConst.NOT_BLANK_MESSAGE;
import static com.aston.frontendpracticeservice.dto.ValidationMessageConst.PASSPORT_SIZE_MESSAGE;
import static com.aston.frontendpracticeservice.dto.ValidationMessageConst.PAST_MESSAGE;
import static com.aston.frontendpracticeservice.dto.RegExpConst.REGEXP_INN;
import static com.aston.frontendpracticeservice.dto.RegExpConst.REGEXP_PASSPORT;
import static com.aston.frontendpracticeservice.dto.RegExpConst.REGEXP_SNILS;
import static com.aston.frontendpracticeservice.dto.ValidationMessageConst.SNILS_SIZE_MESSAGE;

@Schema(description = "Объект для передачи данных о клиенте для создания или обновления")
public record UserRequest(

        @NotBlank(message = NOT_BLANK_MESSAGE)
        @Schema(description = DESCRIPTION_FIRSTNAME, example = EXAMPLE_FIRSTNAME)
        String firstname,

        @NotBlank(message = NOT_BLANK_MESSAGE)
        @Schema(description = DESCRIPTION_LASTNAME, example = EXAMPLE_LASTNAME)
        String lastname,

        @Past(message = PAST_MESSAGE)
        @Schema(description = DESCRIPTION_BIRTH_DATE, example = EXAMPLE_BIRTH_DATE)
        LocalDate birthDate,

        @Pattern(regexp = REGEXP_INN, message = INN_SIZE_MESSAGE)
        @Schema(description = DESCRIPTION_NUMBER_INN, example = EXAMPLE_NUMBER_INN)
        String numberInn,

        @Pattern(regexp = REGEXP_SNILS, message = SNILS_SIZE_MESSAGE)
        @Schema(description = DESCRIPTION_SNILS, example = EXAMPLE_SNILS)
        String snils,

        @Pattern(regexp = REGEXP_PASSPORT, message = PASSPORT_SIZE_MESSAGE)
        @Schema(description = DESCRIPTION_PASSPORT_NUMBER, example = EXAMPLE_PASSPORT_NUMBER)
        String passportNumber,

        @NotBlank(message = NOT_BLANK_MESSAGE)
        @Schema(description = DESCRIPTION_PASSWORD, example = EXAMPLE_LOGIN)
        String login,

        @NotBlank(message = NOT_BLANK_MESSAGE)
        @Schema(description = DESCRIPTION_ROLE, example = EXAMPLE_PASSWORD)
        String password
) {
}
