package com.aston.frontendpracticeservice.integration.repository;

import com.aston.frontendpracticeservice.integration.IntegrationTestBase;
import com.aston.frontendpracticeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        var maybeUser = userRepository.findByLogin("ivan_petrov");
        assertTrue(maybeUser.isPresent());

        var user = maybeUser.get();
        assertAll(
                () -> assertNotNull(user),
                () -> assertThat(user.getRoles()).hasSize(2),
                () -> assertThat(user.getLogin()).isEqualTo("ivan_petrov")
        );
    }
}
