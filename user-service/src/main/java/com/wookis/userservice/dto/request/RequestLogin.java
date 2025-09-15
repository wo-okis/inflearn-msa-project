package com.wookis.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestLogin {

    @NotBlank(message = "Email cannot be blank")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String password;

}
