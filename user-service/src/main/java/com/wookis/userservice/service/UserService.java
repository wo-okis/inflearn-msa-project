package com.wookis.userservice.service;

import com.wookis.userservice.dto.response.UserDto;
import com.wookis.userservice.entity.UserEntity;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUserByAll();
}
