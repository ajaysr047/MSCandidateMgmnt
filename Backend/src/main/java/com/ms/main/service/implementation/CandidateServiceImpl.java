package com.ms.main.service.implementation;

import com.ms.main.constants.Constants;
import com.ms.main.entity.*;
import com.ms.main.repository.*;
import com.ms.main.request.AddCandidate;
import com.ms.main.response.AddCandidateResponse;
import com.ms.main.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    InstitutionRepository institutionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SkillRepository skillRepository;

    @Override
    public AddCandidateResponse addCandidate(AddCandidate candidate) {
        if(candidateRepository.findByEmail(candidate.getEmail()).isEmpty()){

            Optional<Location> location = locationRepository.findById(candidate.getJoiningLocationId());
            Optional<Institution> institution = institutionRepository.findById(candidate.getInstitutionId());
            Optional<User> createdByUser = userRepository.findById(candidate.getCreatedUserId());

            if(location.isEmpty()){
                return new AddCandidateResponse(false, Constants.ADD_CANDIDATE_FAILURE_LOCATION_MESSAGE, Constants.EMPTY_RESPONSE_STRING, Constants.FAILURE_CANDIDATE_ID);
            }else if(institution.isEmpty()){
                return new AddCandidateResponse(false, Constants.ADD_CANDIDATE_FAILURE_INSTITUTION_MESSAGE, Constants.EMPTY_RESPONSE_STRING, Constants.FAILURE_CANDIDATE_ID);
            }else if(createdByUser.isEmpty()){
                return new AddCandidateResponse(false, Constants.ADD_CANDIDATE_FAILURE_USER_MESSAGE, Constants.EMPTY_RESPONSE_STRING, Constants.FAILURE_CANDIDATE_ID);
            }else{

                Map<String, String> toReduceParamCount = new HashMap<>();
                toReduceParamCount.put("name", candidate.getName());
                toReduceParamCount.put("email", candidate.getEmail());
                toReduceParamCount.put("phone", candidate.getPhoneNumber());
                toReduceParamCount.put("feedback", candidate.getFeedback());
                toReduceParamCount.put("description", candidate.getDescription());

                Candidate persistentCandidate = candidateRepository.save(new Candidate( toReduceParamCount, location.get(), institution.get(), createdByUser.get(), true));


                Integer candidateId = persistentCandidate.getCandidateId();

                candidate.getSkillSet().forEach(skill -> skillRepository.save(new Skill(candidateId, skill)));

                return new AddCandidateResponse(true, Constants.ADD_CANDIDATE_SUCCESS_MESSAGE, persistentCandidate.getName(), createdByUser.get().getUserId());
            }
        }
        return new AddCandidateResponse(false, Constants.ADD_CANDIDATE_FAILURE_DUPLICATE_MESSAGE, Constants.EMPTY_RESPONSE_STRING, Constants.FAILURE_CANDIDATE_ID);
    }
}
