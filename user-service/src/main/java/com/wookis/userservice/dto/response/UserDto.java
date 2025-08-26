package com.wookis.userservice.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private String email;

    private String pwd;

    private String name;

    private String userId;

    private LocalDate createdAt;

    private String encryptedPwd;
}
