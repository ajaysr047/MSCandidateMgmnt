package com.ms.main.service.implementation;

import com.ms.main.constants.Constants;
import com.ms.main.entity.*;
import com.ms.main.repository.*;
import com.ms.main.request.AddCandidate;
import com.ms.main.request.UpdateCandidate;
import com.ms.main.response.AddCandidateResponse;
import com.ms.main.response.DeleteCandidateResponse;
import com.ms.main.response.GetAllCandidateResponse;
import com.ms.main.response.UpdateCandidateResponse;
import com.ms.main.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

                return new AddCandidateResponse(true, Constants.ADD_CANDIDATE_SUCCESS_MESSAGE, persistentCandidate.getName(), persistentCandidate.getCandidateId());
            }
        }
        return new AddCandidateResponse(false, Constants.ADD_CANDIDATE_FAILURE_DUPLICATE_MESSAGE, Constants.EMPTY_RESPONSE_STRING, Constants.FAILURE_CANDIDATE_ID);
    }

    @Override
    public GetAllCandidateResponse getAllActiveCandidates() {
        List<Candidate> candidateList = candidateRepository.findAll();
        if(candidateList.isEmpty()){

            return new GetAllCandidateResponse(false, Constants.GET_CANDIDATES_FAILURE_MESSAGE, candidateList);
        }
        GetAllCandidateResponse response = new GetAllCandidateResponse();
        response.setSuccess(true);
        response.setMessage(Constants.GET_CANDIDATES_SUCCESS_MESSAGE);

        candidateList.forEach(candidate -> {if(candidate.isActive()){
                response.getCandidateList().add(candidate);
            }
        });

        return response;
    }

    @Override
    public GetAllCandidateResponse getAllCandidates() {
        List<Candidate> candidateList = candidateRepository.findAll();
        if(candidateList.isEmpty()){
            return new GetAllCandidateResponse(false, Constants.GET_CANDIDATES_FAILURE_MESSAGE, candidateList);
        }
        return new GetAllCandidateResponse(true, Constants.GET_CANDIDATES_SUCCESS_MESSAGE, candidateList);
    }

    @Override
    public DeleteCandidateResponse deleteCandidate(Integer id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if(candidate.isEmpty()){
            return new DeleteCandidateResponse(false, Constants.DELETE_CANDIDATE_FAILURE_MESSAGE);
        }
        candidate.get().setActive(false);
        candidateRepository.save(candidate.get());

        return  new DeleteCandidateResponse(true, Constants.DELETE_CANDIDATE_SUCCESS_MESSAGE);
    }

    @Override
    @Transactional
    public UpdateCandidateResponse updateCandidate(UpdateCandidate candidate) {
        Optional<Candidate> persistentCandidate = candidateRepository.findByEmail(candidate.getEmail());

        if(persistentCandidate.isEmpty()){

            return new UpdateCandidateResponse(false, Constants.UPDATE_CANDIDATE_FAILURE_MESSAGE);
        }

        Integer candidateId = persistentCandidate.get().getCandidateId();
        skillRepository.deleteAllByCandidateId(candidateId);

        candidate.getSkillSet().forEach(skill -> skillRepository.save(new Skill(persistentCandidate.get().getCandidateId(), skill)));

        persistentCandidate.get().setDescription(candidate.getDescription());
        persistentCandidate.get().setPhoneNumber(candidate.getPhoneNumber());
        persistentCandidate.get().setFeedback(candidate.getFeedback());
        persistentCandidate.get().setActive(candidate.isActive());

        candidateRepository.save(persistentCandidate.get());

        return new UpdateCandidateResponse(true, Constants.UPDATE_CANDIDATE_SUCCESS_MESSAGE);
    }
}
