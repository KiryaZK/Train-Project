package com.aston.frontendpracticeservice.mapper;

import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.dto.user.UserRequest;
import com.aston.frontendpracticeservice.dto.user.UserResponse;
import com.aston.frontendpracticeservice.utils.PasswordEncoderUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);

    @Mapping(source = "password", target = "password", qualifiedByName = "getEncodePassword")
    void toUser(UserRequest userRequest, @MappingTarget User user);

    @Named("getEncodePassword")
    default String getEncodePassword(String password) {
        return PasswordEncoderUtil.encodePassword(password);
    }
}
