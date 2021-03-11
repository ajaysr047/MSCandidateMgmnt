package com.ms.main.service.implementation;

import com.ms.main.constants.Constants;
import com.ms.main.entity.*;
import com.ms.main.repository.*;
import com.ms.main.request.AddCandidate;
import com.ms.main.request.UpdateCandidate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CandidateServiceImplTest {

    @InjectMocks
    CandidateServiceImpl candidateService;

    @Mock
    CandidateRepository candidateRepository;

    @Mock
    LocationRepository locationRepository;

    @Mock
    InstitutionRepository institutionRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    SkillRepository skillRepository;


    @Test
    void addCandidateTest() {
        Mockito.when(candidateRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(new Candidate()));

        Assertions.assertEquals(Constants.ADD_CANDIDATE_FAILURE_DUPLICATE_MESSAGE, candidateService.addCandidate(new AddCandidate()).getMessage());

        Mockito.when(candidateRepository.findByEmail(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(locationRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertEquals(Constants.ADD_CANDIDATE_FAILURE_LOCATION_MESSAGE, candidateService.addCandidate(new AddCandidate()).getMessage());

        Mockito.when(locationRepository.findById(Mockito.any())).thenReturn(Optional.of(new Location()));
        Mockito.when(institutionRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertEquals(Constants.ADD_CANDIDATE_FAILURE_INSTITUTION_MESSAGE, candidateService.addCandidate(new AddCandidate()).getMessage());

        Mockito.when(institutionRepository.findById(Mockito.any())).thenReturn(Optional.of(new Institution()));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertEquals(Constants.ADD_CANDIDATE_FAILURE_USER_MESSAGE, candidateService.addCandidate(new AddCandidate()).getMessage());

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(new User()));
        Mockito.when(candidateRepository.save(Mockito.any(Candidate.class))).thenReturn(new Candidate());
        Assertions.assertEquals(Constants.ADD_CANDIDATE_SUCCESS_MESSAGE, candidateService.addCandidate(new AddCandidate()).getMessage());

    }

    @Test
    void getAllActiveCandidatesTest() {
        Mockito.when(candidateRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertFalse(candidateService.getAllActiveCandidates().isSuccess());

        List<Candidate> candidateList = new ArrayList<>();
        Candidate candidate = new Candidate();
        candidate.setActive(true);


        candidateList.add(new Candidate());
        candidateList.add(candidate);
        Mockito.when(candidateRepository.findAll()).thenReturn(candidateList);

        Assertions.assertTrue(candidateService.getAllActiveCandidates().isSuccess());
    }

    @Test
    void getAllCandidatesTest() {
        Mockito.when(candidateRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertFalse(candidateService.getAllCandidates().isSuccess());

        List<Candidate> candidateList = new ArrayList<>();
        candidateList.add(new Candidate());

        Mockito.when(candidateRepository.findAll()).thenReturn(candidateList);

        Assertions.assertTrue(candidateService.getAllCandidates().isSuccess());
    }

    @Test
    void deleteCandidateTest() {
        Mockito.when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertFalse(candidateService.deleteCandidate(1).isSuccess());

        Mockito.when(candidateRepository.findById(Mockito.any())).thenReturn(Optional.of(new Candidate()));
        Mockito.when(candidateRepository.save(Mockito.any())).thenReturn(new Candidate());
        Assertions.assertTrue(candidateService.deleteCandidate(1).isSuccess());
    }

    @Test
    void updateCandidateTest() {
        Mockito.when(candidateRepository.findByEmail(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertFalse(candidateService.updateCandidate(new UpdateCandidate()).isSuccess());

        Candidate candidate = new Candidate();
        candidate.setCandidateId(1);

        AddCandidate addCandidate = new AddCandidate();
        addCandidate.getSkillSet().add("Test skill");

        Mockito.when(candidateRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(candidate));
        Assertions.assertTrue(candidateService.updateCandidate(new UpdateCandidate()).isSuccess());
    }
}