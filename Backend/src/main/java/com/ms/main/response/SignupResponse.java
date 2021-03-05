package com.ms.main.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponse {
    private Integer userId;

    private boolean isSignedUp;

    private String message;

    public SignupResponse(){}

    public SignupResponse(Integer userId, boolean isSignedUp, String message) {
        this.userId = userId;
        this.isSignedUp = isSignedUp;
        this.message = message;
    }
}
