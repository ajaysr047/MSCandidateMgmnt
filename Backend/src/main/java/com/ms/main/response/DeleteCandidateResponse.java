package com.ms.main.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCandidateResponse {

    private boolean isSuccess;

    private String message;

    public DeleteCandidateResponse() {
    }

    public DeleteCandidateResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
