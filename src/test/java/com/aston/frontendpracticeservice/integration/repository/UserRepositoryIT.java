package com.aston.frontendpracticeservice.integration.repository;

import com.aston.frontendpracticeservice.integration.IntegrationTestBase;
import com.aston.frontendpracticeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.ACCOUNT_NUMBER;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.FIRST_NAME;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.KBK;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.LOGIN;
import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class UserRepositoryIT extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Test
    @DisplayName("Get User by login should return User")
    void getUserByLogin_ShouldReturnUser() {
        var maybeUser = userRepository.findByLogin(LOGIN);
        assertTrue(maybeUser.isPresent());

        var user = maybeUser.get();
        assertAll(
                () -> assertNotNull(user),
                () -> assertThat(user.getRoles()).hasSize(2),
                () -> assertThat(user.getLogin()).isEqualTo(LOGIN)
        );
    }

    @Test
    @DisplayName("Get UserView by id should return UserView")
    void getUserViewById_ShouldReturnUserView() {
        var userViewOptional = userRepository.findUserView(UUID.fromString(USER_ID));

        assertTrue(userViewOptional.isPresent());
        var userView = userViewOptional.get();
        assertAll(
                () -> assertThat(userView.getFirstname())
                        .isEqualTo(FIRST_NAME),
                () -> assertThat(userView.getAccountNumber())
                        .isEqualTo(ACCOUNT_NUMBER),
                () -> assertThat(userView.getKbk())
                        .isEqualTo(KBK)
        );
    }
}
