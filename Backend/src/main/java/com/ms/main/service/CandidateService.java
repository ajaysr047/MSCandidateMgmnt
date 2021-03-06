package com.ms.main.service;

import com.ms.main.request.AddCandidate;
import com.ms.main.response.AddCandidateResponse;
import com.ms.main.response.GetAllCandidateResponse;

public interface CandidateService {
    AddCandidateResponse addCandidate(AddCandidate candidate);
    GetAllCandidateResponse getAllActiveCandidates();
    GetAllCandidateResponse getAllCandidates();

}
