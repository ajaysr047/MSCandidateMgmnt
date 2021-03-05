package com.ms.main.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserSignup {

    @NotEmpty(message = "User name cannot be empty!")
    private String name;

    @NotBlank(message = "User email cannot be empty!")
    @Email
    private String email;

    @NotBlank(message = "User password cannot be empty!")
    private String password;

    @NotBlank(message = "User role cannot be empty!")
    private String role;
}
