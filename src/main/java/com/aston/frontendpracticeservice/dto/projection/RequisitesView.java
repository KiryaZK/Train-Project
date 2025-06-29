package com.aston.frontendpracticeservice.dto.projection;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.aston.frontendpracticeservice.dto.RegExpConst.REGEXP_ACCOUNT_SIZE;
import static com.aston.frontendpracticeservice.dto.ValidationMessageConst.NOT_BLANK_MESSAGE;
import static com.aston.frontendpracticeservice.dto.ValidationMessageConst.NULL_OR_SIZE_MESSAGE;
import static com.aston.frontendpracticeservice.utils.constants.Description.DESCRIPTION_ACCOUNT_NUMBER;
import static com.aston.frontendpracticeservice.utils.constants.Description.DESCRIPTION_KBK;
import static com.aston.frontendpracticeservice.utils.constants.ExampleValues.EXAMPLE_ACCOUNT_NUMBER;
import static com.aston.frontendpracticeservice.utils.constants.ExampleValues.EXAMPLE_KBK;

@Schema(description = "Проекция реквизитов для передачи данных о счете и кбк пользователя")
public record RequisitesView(

        @Pattern(regexp = REGEXP_ACCOUNT_SIZE, message = NOT_BLANK_MESSAGE)
        @Schema(description = DESCRIPTION_ACCOUNT_NUMBER, example = EXAMPLE_ACCOUNT_NUMBER)
        String accountNumber,

        @Size(min = 20, max = 20, message = NULL_OR_SIZE_MESSAGE)
        @Schema(description = DESCRIPTION_KBK, example = EXAMPLE_KBK)
        String kbk
) {}
