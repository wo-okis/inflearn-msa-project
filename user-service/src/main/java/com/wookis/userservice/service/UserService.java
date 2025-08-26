package com.wookis.userservice.service;

import com.wookis.userservice.dto.response.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);
}
