package com.ms.main.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCandidateResponse {

    private boolean isSuccess;

    private String message;

    private String candidateName;

    private Integer candidateId;

    public AddCandidateResponse() {
    }

    public AddCandidateResponse(boolean isSuccess, String message, String candidateName, Integer candidateId) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.candidateName = candidateName;
        this.candidateId = candidateId;
    }
}
