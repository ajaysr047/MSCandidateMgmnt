package com.ms.main.controller;

import com.ms.main.request.AddInstitution;
import com.ms.main.response.AddInstitutionResponse;
import com.ms.main.response.GetAllInstitutionResponse;
import com.ms.main.service.InstitutionService;
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
class InstitutionControllerTest {

    @InjectMocks
    InstitutionController institutionController;

    @Mock
    InstitutionService institutionService;

    @Test
    void addInstitutionTest() {
        AddInstitutionResponse response = new AddInstitutionResponse();
        response.setSuccess(true);

        AddInstitution institution = new AddInstitution();

        Mockito.when(institutionService.addInstitution(Mockito.any(AddInstitution.class))).thenReturn(response);

        Assertions.assertTrue(Objects.requireNonNull(institutionController.addInstitution(institution).getBody()).isSuccess());

        response.setSuccess(false);
        Assertions.assertFalse(Objects.requireNonNull(institutionController.addInstitution(institution).getBody()).isSuccess());

    }

    @Test
    void getAllInstitutionsTest() {
        GetAllInstitutionResponse response = new GetAllInstitutionResponse();
        response.setSuccess(true);

        Mockito.when(institutionService.getAllInstitution()).thenReturn(response);

        Assertions.assertTrue(Objects.requireNonNull(institutionController.getAllInstitutions().getBody()).isSuccess());

        response.setSuccess(false);
        Assertions.assertFalse(Objects.requireNonNull(institutionController.getAllInstitutions().getBody()).isSuccess());
    }
}