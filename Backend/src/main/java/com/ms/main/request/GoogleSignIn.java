package com.ms.main.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GoogleSignIn {
    @NotBlank(message = "Email cannot be empty!")
    @Email
    private String email;
}
