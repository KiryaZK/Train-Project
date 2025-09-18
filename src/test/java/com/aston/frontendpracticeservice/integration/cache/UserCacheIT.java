package com.aston.frontendpracticeservice.integration.cache;

import com.aston.frontendpracticeservice.domain.entity.User;

import com.aston.frontendpracticeservice.exception.business.UserNotFoundException;
import com.aston.frontendpracticeservice.integration.IntegrationTestBase;
import com.aston.frontendpracticeservice.repository.UserRepository;
import com.aston.frontendpracticeservice.service.UserService;
import com.aston.frontendpracticeservice.utils.TestDataFactory;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.aston.frontendpracticeservice.utils.constants.ValueExistDb.USER_ID;
import static com.aston.frontendpracticeservice.utils.constants.ValueNotExistDb.FIRST_NAME_UPDATE;
import static com.aston.frontendpracticeservice.utils.constants.ValueNotExistDb.LAST_NAME_UPDATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RequiredArgsConstructor
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UserCacheIT extends IntegrationTestBase {

    private final UserService userService;

    @SpyBean
    private final UserRepository userRepository;

    private final CacheManager cacheManager;

    private static UUID userId;

    @BeforeEach
    void setUp() {
        var user = TestDataFactory.getUser(false);
        user.setFirstname(FIRST_NAME_UPDATE);
        user.setLastname(LAST_NAME_UPDATE);
        user = userRepository.save(user);
        userId = user.getId();
        Mockito.clearInvocations(userRepository);
    }

    @AfterEach
    void clear() {
        cacheManager.getCacheNames().forEach(cache ->
                cacheManager.getCache(cache).clear());
        userRepository.deleteAll();
        Mockito.clearInvocations(userRepository);
    }

    @Test
    @DisplayName("getById method - should retrieve UserResponse record from cache " +
            "after initial database retrieval")
    void shouldRetrieveUserResponseFromCache() {
        var userId = UUID.fromString(USER_ID);
        var userResponse = userService.getById(userId);

        assertThat(userResponse).isNotNull();
        verify(userRepository, times(1)).findById(userId);

        Mockito.clearInvocations(userRepository);
        userResponse = userService.getById(userId);

        assertThat(userResponse).isNotNull();
        verify(userRepository, times(0)).findById(userId);
    }

    @Test
    @DisplayName("deleteById method - should evict UserResponse record from cache")
    void shouldEvictUserFromCache() {
        var userResponse = userService.getById(userId);
        assertThat(userResponse).isNotNull();
        Mockito.clearInvocations(userRepository);

        userResponse = userService.getById(userId);
        assertThat(userResponse).isNotNull();
        verify(userRepository, times(0)).findById(userId);

        userService.deleteById(userId);
        verify(userRepository, times(1)).deleteById(userId);

        assertThat(cacheManager.getCache("users").get(userId)).isNull();
        assertThrows(UserNotFoundException.class, () -> userService.getById(userId));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("updateUser method - should update UserResponse record from cache")
    void shouldUpdateUserResponseFromCache() {
        var userResponse = userService.getById(userId);
        assertThat(userResponse).isNotNull();
        verify(userRepository, times(1)).findById(userId);
        Mockito.clearInvocations(userRepository);

        var userRequest = TestDataFactory.getUserRequest(false);
        var resultCache = userService.updateUser(userId, userRequest);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).saveAndFlush(any(User.class));

        assertAll(
                () -> assertThat(resultCache.firstname())
                        .isEqualTo(userRequest.firstname()),

                () -> assertThat(resultCache.lastname())
                        .isEqualTo(userRequest.lastname())
        );
    }
}
