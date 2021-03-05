package com.ms.main.service;

import com.ms.main.request.AddCandidate;
import com.ms.main.response.AddCandidateResponse;

public interface CandidateService {
    AddCandidateResponse addCandidate(AddCandidate candidate);
}
