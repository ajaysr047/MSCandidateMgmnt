package com.ms.main.response;

import com.ms.main.entity.Candidate;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllCandidateResponse {

    private boolean isSuccess;

    private String message;

    private List<Candidate> candidateList = new ArrayList<>();

    public GetAllCandidateResponse(boolean isSuccess, String message, List<Candidate> candidateList) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.candidateList = candidateList;
    }

    public GetAllCandidateResponse() {
    }
}
