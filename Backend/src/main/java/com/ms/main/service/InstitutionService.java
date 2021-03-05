package com.ms.main.service;

import com.ms.main.request.AddInstitution;
import com.ms.main.response.AddInstitutionResponse;
import com.ms.main.response.GetAllInstitutionResponse;

public interface InstitutionService {
    AddInstitutionResponse addInstitution(AddInstitution institution);
    GetAllInstitutionResponse getAllInstitution();
}
