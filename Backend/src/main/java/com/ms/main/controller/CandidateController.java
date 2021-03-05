package com.ms.main.controller;


import com.ms.main.request.AddCandidate;
import com.ms.main.response.AddCandidateResponse;
import com.ms.main.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
