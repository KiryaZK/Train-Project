package com.aston.frontendpracticeservice.integration.controller;

import com.aston.frontendpracticeservice.integration.IntegrationTestBase;
import com.aston.frontendpracticeservice.service.UserService;
import com.aston.frontendpracticeservice.utils.TestDataFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.aston.frontendpracticeservice.utils.TestConst.DELETE_USER_BY_ID;
import static com.aston.frontendpracticeservice.utils.TestConst.GET_ALL_USERS_URL;
import static com.aston.frontendpracticeservice.utils.TestConst.GET_USER_BY_ID;
import static com.aston.frontendpracticeservice.utils.TestConst.INCORRECT_USER_ID;
import static com.aston.frontendpracticeservice.utils.TestConst.POST_CREATE_NEW_USER_URL;
import static com.aston.frontendpracticeservice.utils.TestConst.PUT_UPDATE_USER_URL;
import static com.aston.frontendpracticeservice.utils.TestConst.USER_ID;
import static com.aston.frontendpracticeservice.utils.TestConst.USER_NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerIT extends IntegrationTestBase {

    private final MockMvc mvc;

    private final UserService userService;

    private final ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /users - When Users exist - expect success")
    void getAllUsers_ShouldReturnAllUsers() throws Exception {
        var request = get(GET_ALL_USERS_URL);
        var users = userService.getAllUsers();

        mvc.perform(request)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.length()").value(users.size()),
                        content().json(objectMapper.writeValueAsString(users))
                );
    }

    @Test
    @DisplayName("GET /users/{id} - When User exist by ID - expect success")
    void getUserById_ShouldReturnUser() throws Exception {
        var request = get(GET_USER_BY_ID, USER_ID);
        var user = userService.getById(UUID.fromString(USER_ID));

        mvc.perform(request)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(objectMapper.writeValueAsString(user))
                );
    }

    @Test
    @DisplayName("GET /users/{id} - When User exist by ID - expect UserNotFoundException")
    void getUserById_ShouldThrowException() throws Exception {
        var request = get(GET_USER_BY_ID, INCORRECT_USER_ID);

        mvc.perform(request)
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(USER_NOT_FOUND)
                );
    }

    @Test
    @DisplayName("POST /users - When input data is valid - expect success")
    void createNewUser_ShouldReturnSuccess() throws Exception {
        var userRequest = TestDataFactory.getUserRequest();
        var request = post(POST_CREATE_NEW_USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest));

        mvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PUT /users/{id} - When input data is valid - expect updated User")
    void updateUser_ShouldReturnSuccess() throws Exception {
        var userRequest = TestDataFactory.getUserRequest();
        var request = put(PUT_UPDATE_USER_URL, USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest));

        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /users/{id} - When user's id exists - expect success")
    void deleteUserById_ShouldDeleteUser() throws Exception {
        var request = delete(DELETE_USER_BY_ID, USER_ID);

        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /users/{id} - When user's id is invalid - expect exception")
    void deleteUserById_ShouldThrowException() throws Exception {
        var request = delete(DELETE_USER_BY_ID, "fail");

        mvc.perform(request).andExpect(status().is4xxClientError());
    }
}