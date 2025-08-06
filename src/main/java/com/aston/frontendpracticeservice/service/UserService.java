package com.aston.frontendpracticeservice.service;

import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.dto.projection.UserAccountDetailsView;
import com.aston.frontendpracticeservice.dto.user.UserRequest;
import com.aston.frontendpracticeservice.dto.user.UserResponse;
import com.aston.frontendpracticeservice.exception.business.UserNotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с пользователями
 */
public interface UserService {
    /**
     * Метод для получения всех пользователей
     * @return список DTO всех пользователей
     * @throws UserNotFoundException если пользователи не найдены
     */
    List<UserResponse> getAllUsers();

    /**
     * Метод для получения пользователя
     * @param id - UUID пользователя
     * @return DTO с информацией о пользователе
     * @throws UserNotFoundException если пользователь не найден по id
     */
    UserResponse getById(UUID id);

    /**
     * Метод для получения пользователя по логину
     * @param login - логин пользователя
     * @return DTO с информацией о пользователе
     * @throws UserNotFoundException если пользователь не найден по логину
     */
    User getByLogin(String login);

    /**
     * Метод для создания нового пользователя
     * @param userDTO - DTO с данными нового пользователя
     */
    void createNewUser(UserRequest userDTO);

    /**
     * Метод для обновления данных существующего пользователя
     *
     * @param id      - UUID пользователя
     * @param userDTO - DTO с новыми данными пользователя
     * @return DTO с информацией о пользователе
     * @throws UserNotFoundException если пользователь не найден по id
     */
    UserResponse updateUser(UUID id, UserRequest userDTO);

    /**
     * Метод для удаления пользователя по его ID
     * @param id - UUID пользователя
     */
    void deleteById(UUID id);

    /**
     * Метод для получения имени пользователя, его расчетного счета и кбк
     * по его ID
     * @param id - UUID пользователя
     * @return Проекция с именем, расчетным счетом и кбк пользователя
     */
    UserAccountDetailsView getUserViewById(UUID id);
}
