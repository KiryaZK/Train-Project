package com.aston.frontendpracticeservice.unit.service;

import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.dto.user.UserRequest;
import com.aston.frontendpracticeservice.dto.user.UserResponse;
import com.aston.frontendpracticeservice.exception.UserNotFoundException;
import com.aston.frontendpracticeservice.mapper.UserMapper;
import com.aston.frontendpracticeservice.repository.UserRepository;
import com.aston.frontendpracticeservice.service.implementation.UserServiceImpl;
import com.aston.frontendpracticeservice.utils.TestDataFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.aston.frontendpracticeservice.utils.TestConst.USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    static UUID id;

    @BeforeAll
    static void setUp() {
        id = UUID.fromString(USER_ID);
    }

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    private UserResponse userResponse;

    private UserRequest userRequest;

    @BeforeEach
    void createDataForTest() {
        user = TestDataFactory.getUser();
        userResponse = TestDataFactory.getUserResponse();
        userRequest = TestDataFactory.getUserRequest();
    }

    @Test
    @DisplayName("Get all users should return list with userResponses")
    void getAllUsers_ShouldReturnAllUsers() {
        var users = List.of(
                TestDataFactory.getUser(),
                TestDataFactory.getUser(),
                TestDataFactory.getUser()
        );
        var responseList = List.of(
                TestDataFactory.getUserResponse(),
                TestDataFactory.getUserResponse(),
                TestDataFactory.getUserResponse()
        );

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toUserResponseList(anyList())).thenReturn(responseList);
        var result = userService.getAllUsers();

        assertThat(result).hasSize(users.size());
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).toUserResponseList(users);
    }

    @Test
    @DisplayName("Get all users should throw UserNotFoundException")
    void getAllUsers_ShouldThrowUserNotFoundException() {
        List<User> users = Collections.emptyList();

        when(userRepository.findAll()).thenReturn(users);

        assertThrows(UserNotFoundException.class, () -> userService.getAllUsers());
        verify(userRepository, times(1)).findAll();
        verify(userMapper, never()).toUserResponseList(anyList());
    }

    @Test
    @DisplayName("Get user should return userResponse")
    void getById_ShouldReturnUser() {
        when(userRepository.findById(id))
                .thenReturn(Optional.of(user));
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);
        var result = userService.getById(id);

        assertThat(result).isEqualTo(userResponse);
        verify(userRepository, times(1))
                .findById(id);
        verify(userMapper, times(1))
                .toUserResponse(user);
    }

    @Test
    @DisplayName("Get user should throw UserNotFoundException")
    void getById_ShouldThrowUserNotFoundException() {
        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.getById(id));
        verify(userRepository, times(1))
                .findById(id);
        verify(userMapper, never())
                .toUserResponse(any(User.class));
    }

    @Test
    @DisplayName("Create a new User should create a new User")
    void createNewUser_ShouldCreateNewUser() {
        userService.createNewUser(userRequest);

        verify(userMapper, times(1))
                .toUser(any(UserRequest.class), any(User.class));
        verify(userRepository, times(1))
                .save(any(User.class));
    }

    @Test
    @DisplayName("Update a User should return the updated User")
    void updateUser_ShouldReturnUser() {
        when(userRepository.findById(id))
                .thenReturn(Optional.of(user));
        doNothing().when(userMapper).toUser(userRequest, user);
        userService.updateUser(id, userRequest);

        verify(userRepository).findById(id);
        verify(userMapper).toUser(userRequest, user);
    }

    @Test
    @DisplayName("Update a User When User not exists throws exception")
    void updateUser_ShouldThrowUserNotFoundException() {
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.updateUser(id, any(UserRequest.class)));
        verify(userRepository).findById(id);
        verify(userMapper, never()).toUser(any(UserRequest.class), any());
    }

    @Test
    @DisplayName("Delete user by ID should expect success deletion")
    void deleteById_ShouldDeleteUser() {
        doNothing().when(userRepository).deleteById(any(UUID.class));
        userService.deleteById(id);
        verify(userRepository, times(1))
                .deleteById(any(UUID.class));
    }
}
