package com.aston.frontendpracticeservice.unit.mapper;

import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.mapper.UserMapper;
import com.aston.frontendpracticeservice.utils.PasswordEncoderUtil;
import com.aston.frontendpracticeservice.utils.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    @DisplayName("Convert User to UserResponse")
    void toUserResponse_ShouldReturnValidUserResponse() {
        var user = TestDataFactory.getUser();
        var userResponse = userMapper.toUserResponse(user);

        assertAll(
                () -> assertNotNull(userResponse),

                () -> assertThat(userResponse.firstname())
                        .isEqualTo(user.getFirstname()),

                () -> assertThat(userResponse.lastname())
                        .isEqualTo(user.getLastname()),

                () -> assertThat(userResponse.birthDate())
                        .isEqualTo(user.getBirthDate()),

                () -> assertThat(userResponse.numberInn())
                        .isEqualTo(user.getNumberInn()),

                () -> assertThat(userResponse.snils())
                        .isEqualTo(user.getSnils()),

                () -> assertThat(userResponse.passportNumber())
                        .isEqualTo(user.getPassportNumber()),

                () -> assertThat(userResponse.roles())
                        .hasSize(user.getRoles().size())
        );
    }

    @Test
    @DisplayName("Convert list of User to list of UserResponse")
    void toUserResponseList_ShouldReturnValidUserResponseList() {
        var users = new ArrayList<User>() {{
            add(TestDataFactory.getUser());
        }};
        var userResponseList = userMapper.toUserResponseList(users);

        assertAll(
                () -> assertNotNull(userResponseList),
                () -> assertFalse(userResponseList.isEmpty()),
                () -> assertThat(userResponseList).hasSize(users.size())
        );
    }

    @Test
    @DisplayName("Convert UserRequest to User")
    void toUser_ShouldReturnValidUser() {
        var userRequest = TestDataFactory.getUserRequest();
        User user = new User();
        user.addRole();

        userMapper.toUser(userRequest, user);

        assertAll(
                () -> assertThat(user.getFirstname())
                        .isEqualTo(userRequest.firstname()),

                () -> assertThat(user.getLastname())
                        .isEqualTo(userRequest.lastname()),

                () -> assertThat(user.getBirthDate())
                        .isEqualTo(userRequest.birthDate()),

                () -> assertThat(user.getNumberInn())
                        .isEqualTo(userRequest.numberInn()),

                () -> assertThat(user.getSnils())
                        .isEqualTo(userRequest.snils()),

                () -> assertThat(user.getPassportNumber())
                        .isEqualTo(userRequest.passportNumber()),

                () -> assertEquals(1, user.getRoles().size()),

                () -> assertThat(user.getLogin())
                        .isEqualTo(userRequest.login()),

                () -> assertTrue(PasswordEncoderUtil.
                        matches(userRequest.password(), user.getPassword()))
        );
    }

}
