package com.ms.main.controller;


import com.ms.main.request.AddCandidate;
import com.ms.main.response.AddCandidateResponse;
import com.ms.main.response.GetAllCandidateResponse;
import com.ms.main.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping("/add")
    public ResponseEntity<AddCandidateResponse> addCandidate(@Valid @RequestBody AddCandidate candidate){
        AddCandidateResponse response = candidateService.addCandidate(candidate);
        if(response.isSuccess()){
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<GetAllCandidateResponse> getAllCandidates(){
        GetAllCandidateResponse response = candidateService.getAllCandidates();
        if(response.isSuccess()){

            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllActive")
    public ResponseEntity<GetAllCandidateResponse> getAllActiveCandidates(){
        GetAllCandidateResponse response = candidateService.getAllActiveCandidates();
        if(response.isSuccess()){

            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
