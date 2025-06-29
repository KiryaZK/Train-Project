package com.aston.frontendpracticeservice.dto.user;

import com.aston.frontendpracticeservice.security.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.Set;

import static com.aston.frontendpracticeservice.dto.RegExpConst.REGEXP_PASSPORT;
import static com.aston.frontendpracticeservice.dto.RegExpConst.REGEXP_SNILS;
import static com.aston.frontendpracticeservice.dto.ValidationMessageConst.NOT_BLANK_MESSAGE;
import static com.aston.frontendpracticeservice.dto.ValidationMessageConst.PASSPORT_SIZE_MESSAGE;
import static com.aston.frontendpracticeservice.dto.ValidationMessageConst.PAST_MESSAGE;
import static com.aston.frontendpracticeservice.dto.ValidationMessageConst.SNILS_SIZE_MESSAGE;
import static com.aston.frontendpracticeservice.utils.constants.Description.DESCRIPTION_BIRTH_DATE;
import static com.aston.frontendpracticeservice.utils.constants.Description.DESCRIPTION_FIRSTNAME;
import static com.aston.frontendpracticeservice.utils.constants.Description.DESCRIPTION_LASTNAME;
import static com.aston.frontendpracticeservice.utils.constants.Description.DESCRIPTION_LOGIN;
import static com.aston.frontendpracticeservice.utils.constants.Description.DESCRIPTION_PASSPORT_NUMBER;
import static com.aston.frontendpracticeservice.utils.constants.Description.DESCRIPTION_SNILS;
import static com.aston.frontendpracticeservice.utils.constants.ExampleValues.EXAMPLE_BIRTH_DATE;
import static com.aston.frontendpracticeservice.utils.constants.ExampleValues.EXAMPLE_FIRSTNAME;
import static com.aston.frontendpracticeservice.utils.constants.ExampleValues.EXAMPLE_LASTNAME;
import static com.aston.frontendpracticeservice.utils.constants.ExampleValues.EXAMPLE_PASSPORT_NUMBER;
import static com.aston.frontendpracticeservice.utils.constants.ExampleValues.EXAMPLE_ROLE;
import static com.aston.frontendpracticeservice.utils.constants.ExampleValues.EXAMPLE_SNILS;

@Schema(description = "Объект для передачи информации о пользователе")
public record UserResponse (

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Schema(description = DESCRIPTION_FIRSTNAME, example = EXAMPLE_FIRSTNAME)
    String firstname,

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Schema(description = DESCRIPTION_LASTNAME, example = EXAMPLE_LASTNAME)
    String lastname,

    @Past(message = PAST_MESSAGE)
    @Schema(description = DESCRIPTION_BIRTH_DATE, example = EXAMPLE_BIRTH_DATE)
    LocalDate birthDate,

    @Pattern(regexp = REGEXP_SNILS, message = SNILS_SIZE_MESSAGE)
    @Schema(description = DESCRIPTION_SNILS, example = EXAMPLE_SNILS)
    String snils,

    @Pattern(regexp = REGEXP_PASSPORT, message = PASSPORT_SIZE_MESSAGE)
    @Schema(description = DESCRIPTION_PASSPORT_NUMBER, example = EXAMPLE_PASSPORT_NUMBER)
    String passportNumber,

    @NotEmpty(message = NOT_BLANK_MESSAGE)
    @Schema(description = DESCRIPTION_LOGIN, example = EXAMPLE_ROLE)
    Set<Role> roles
    )
{}
