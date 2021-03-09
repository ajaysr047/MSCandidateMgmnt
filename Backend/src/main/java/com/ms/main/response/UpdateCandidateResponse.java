package com.ms.main.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCandidateResponse {

    private boolean isSuccess;

    public UpdateCandidateResponse() {
    }

    public UpdateCandidateResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    private String message;
}
