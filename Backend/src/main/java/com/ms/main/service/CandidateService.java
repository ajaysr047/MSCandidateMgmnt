package com.ms.main.service;

import com.ms.main.request.AddCandidate;
import com.ms.main.request.UpdateCandidate;
import com.ms.main.response.AddCandidateResponse;
import com.ms.main.response.DeleteCandidateResponse;
import com.ms.main.response.GetAllCandidateResponse;
import com.ms.main.response.UpdateCandidateResponse;

public interface CandidateService {
    AddCandidateResponse addCandidate(AddCandidate candidate);
    GetAllCandidateResponse getAllActiveCandidates();
    GetAllCandidateResponse getAllCandidates();
    DeleteCandidateResponse deleteCandidate(Integer id);
    UpdateCandidateResponse updateCandidate(UpdateCandidate candidate);
}
