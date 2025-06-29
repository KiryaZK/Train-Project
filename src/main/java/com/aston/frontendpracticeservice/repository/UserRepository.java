package com.aston.frontendpracticeservice.repository;

import com.aston.frontendpracticeservice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий пользователей
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Поиск пользователя по логину
     * @param login - логин пользователя
     * @return Optional с найденным пользователем или пустой Optional, если запись не найдена
     */
    Optional<User> findByLogin(String login);

}
