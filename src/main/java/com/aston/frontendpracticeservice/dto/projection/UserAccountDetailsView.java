package com.aston.frontendpracticeservice.dto.projection;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Проекция пользователей для передачи данных " +
        "с именем пользователя и частичными реквизитами")
public interface UserAccountDetailsView {

        String getFirstname();

        String getAccountNumber();

        String getKbk();
}

