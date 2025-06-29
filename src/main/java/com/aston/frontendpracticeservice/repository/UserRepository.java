package com.aston.frontendpracticeservice.repository;

import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.dto.projection.UserAccountDetailsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    /**
     * Поиск имени, расчетного счета и кбк пользователя по его ID
     * @param id - UUID пользователя
     * @return Проекция с именем, расчетным счетом и кбк пользователя
     */
    @Query("SELECT u.firstname firstname, r.accountNumber accountNumber, r.kbk kbk  " +
            "FROM User u JOIN u.requisites r " +
            "WHERE u.id = :id")
    Optional<UserAccountDetailsView> findUserView(UUID id);
}
