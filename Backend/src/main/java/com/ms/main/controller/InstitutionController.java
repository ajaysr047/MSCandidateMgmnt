package com.ms.main.controller;


import com.ms.main.request.AddInstitution;
import com.ms.main.response.AddInstitutionResponse;
import com.ms.main.response.GetAllInstitutionResponse;
import com.ms.main.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/institution")
public class InstitutionController {

    @Autowired
    InstitutionService institutionService;

    @PostMapping("/add")
    public ResponseEntity<AddInstitutionResponse> addInstitution(@Valid @RequestBody AddInstitution institution){

        AddInstitutionResponse response = institutionService.addInstitution(institution);

        if(response.isSuccess()){

            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<GetAllInstitutionResponse> getAllInstitutions(){
        GetAllInstitutionResponse response = institutionService.getAllInstitution();

        if(response.isSuccess()){

            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
