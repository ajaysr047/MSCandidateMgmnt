package com.ms.main.controller;

import com.ms.main.request.AddCandidate;
import com.ms.main.request.UpdateCandidate;
import com.ms.main.response.AddCandidateResponse;
import com.ms.main.response.DeleteCandidateResponse;
import com.ms.main.response.GetAllCandidateResponse;
import com.ms.main.response.UpdateCandidateResponse;
import com.ms.main.service.CandidateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CandidateControllerTest {

    @InjectMocks
    CandidateController candidateController;

    @Mock
    CandidateService candidateService;


    @Test
    void addCandidateTest() {
        AddCandidateResponse response =  new AddCandidateResponse();
        response.setSuccess(true);

        AddCandidate candidate = new AddCandidate();

        Mockito.when(candidateService.addCandidate(Mockito.any(AddCandidate.class))).thenReturn(response);

        Assertions.assertTrue(Objects.requireNonNull(candidateController.addCandidate(candidate).getBody()).isSuccess());

        response.setSuccess(false);
        Assertions.assertFalse(Objects.requireNonNull(candidateController.addCandidate(candidate).getBody()).isSuccess());
    }

    @Test
    void getAllCandidatesTest() {
        GetAllCandidateResponse response = new GetAllCandidateResponse();
        response.setSuccess(true);

        Mockito.when(candidateService.getAllCandidates()).thenReturn(response);

        Assertions.assertTrue(Objects.requireNonNull(candidateController.getAllCandidates().getBody()).isSuccess());

        response.setSuccess(false);
        Assertions.assertFalse(Objects.requireNonNull(candidateController.getAllCandidates().getBody()).isSuccess());
    }

    @Test
    void getAllActiveCandidatesTest() {

        GetAllCandidateResponse response = new GetAllCandidateResponse();
        response.setSuccess(true);

        Mockito.when(candidateService.getAllActiveCandidates()).thenReturn(response);

        Assertions.assertTrue(Objects.requireNonNull(candidateController.getAllActiveCandidates().getBody()).isSuccess());

        response.setSuccess(false);
        Assertions.assertFalse(Objects.requireNonNull(candidateController.getAllActiveCandidates().getBody()).isSuccess());
    }

    @Test
    void deleteCandidateTest() {
        DeleteCandidateResponse response = new DeleteCandidateResponse();
        response.setSuccess(true);

        Mockito.when(candidateService.deleteCandidate(Mockito.any(Integer.class))).thenReturn(response);

        Assertions.assertTrue(Objects.requireNonNull(candidateController.deleteCandidate(1).getBody()).isSuccess());

        response.setSuccess(false);
        Assertions.assertFalse(Objects.requireNonNull(candidateController.deleteCandidate(1).getBody()).isSuccess());
    }

    @Test
    void updateCandidateTest() {
        UpdateCandidateResponse response = new UpdateCandidateResponse();
        response.setSuccess(true);

        UpdateCandidate candidate = new UpdateCandidate();

        Mockito.when(candidateService.updateCandidate(Mockito.any(UpdateCandidate.class))).thenReturn(response);

        Assertions.assertTrue(Objects.requireNonNull(candidateController.updateCandidate(candidate).getBody()).isSuccess());

        response.setSuccess(false);
        Assertions.assertFalse(Objects.requireNonNull(candidateController.updateCandidate(candidate).getBody()).isSuccess());
    }
}