package com.aston.frontendpracticeservice.controller;

import com.aston.frontendpracticeservice.dto.user.UserRequest;
import com.aston.frontendpracticeservice.dto.user.UserResponse;
import com.aston.frontendpracticeservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/puppet-lab")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    @Operation(summary = "Получение всех пользователей",
            description = "Метод позволяет получить список всех пользователей")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Получение пользователя по его ID",
            description = "Метод позволяет получить пользователя по его ID")
    public UserResponse getUserById(@PathVariable UUID id) {
        return userService.getById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание нового пользователя",
            description = "Метод позволяет создать нового пользователя")
    public void createNewUser(@RequestBody @Valid UserRequest userDTO) {
        userService.createNewUser(userDTO);
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "Обновить пользователя",
            description = "Метод позволяет обновить существующего пользователя")
    public void updateUser(@PathVariable UUID id,
                           @RequestBody @Valid UserRequest userDTO) {
        userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Удалить пользователя по его ID",
            description = "Метод позволяет удалить пользователя по его ID")
    public void deleteUserById(@PathVariable UUID id) {
        userService.deleteById(id);
    }
}
