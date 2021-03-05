package com.ms.main.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponse {

    private boolean isValid;

    private String name;

    private String email;

    private Integer userId;

    private String role;

    private String message;

    public SignInResponse(boolean isValid, String name, String email, Integer userId, String role, String message) {
        this.isValid = isValid;
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.role = role;
        this.message = message;
    }

    public SignInResponse() {
    }
}
