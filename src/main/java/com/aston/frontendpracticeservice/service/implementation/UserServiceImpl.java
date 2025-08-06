package com.aston.frontendpracticeservice.service.implementation;

import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.dto.projection.UserAccountDetailsView;
import com.aston.frontendpracticeservice.dto.user.UserRequest;
import com.aston.frontendpracticeservice.dto.user.UserResponse;
import com.aston.frontendpracticeservice.exception.UserNotFoundException;
import com.aston.frontendpracticeservice.mapper.UserMapper;
import com.aston.frontendpracticeservice.repository.UserRepository;
import com.aston.frontendpracticeservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.aston.frontendpracticeservice.utils.constants.ExceptionMessage.USERS_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse> getAllUsers() {
        var users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException(USERS_NOT_FOUND_MESSAGE);
        }
        return userMapper.toUserResponseList(users);
    }

    @Cacheable(value = "users", key = "#id")
    @Transactional(readOnly = true)
    @Override
    public UserResponse getById(UUID id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return userMapper.toUserResponse(user);
    }

    @Transactional
    @Override
    public void createNewUser(UserRequest userDTO) {
        User user = new User();
        userMapper.toUser(userDTO, user);
        user.getRequisites().setUser(user);
        userRepository.save(user);
    }

    @CachePut(value = "users", key = "#id")
    @Transactional
    @Override
    public UserResponse updateUser(UUID id, UserRequest userDTO) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userMapper.toUser(userDTO, user);
        userRepository.saveAndFlush(user);

        return userMapper.toUserResponse(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
    }

    @CacheEvict(value = "users", key = "#id")
    @Transactional
    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserAccountDetailsView getUserViewById(UUID id) {
        return userRepository.findUserView(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
