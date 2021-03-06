package com.ms.main.service.implementation;

import com.ms.main.constants.Constants;
import com.ms.main.entity.Institution;
import com.ms.main.entity.Location;
import com.ms.main.repository.InstitutionRepository;
import com.ms.main.repository.LocationRepository;
import com.ms.main.request.AddInstitution;
import com.ms.main.response.AddInstitutionResponse;
import com.ms.main.response.GetAllInstitutionResponse;
import com.ms.main.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    InstitutionRepository institutionRepository;

    @Autowired
    LocationRepository locationRepository;

    @Override
    public AddInstitutionResponse addInstitution(AddInstitution institution) {
        Optional<Location> location = locationRepository.findByName(institution.getLocationName());

        if(location.isPresent()){
            Institution persistentInstitution = institutionRepository.save(new Institution(institution.getName(), location.get()));
            return new AddInstitutionResponse(true, persistentInstitution.getInstitutionId(), persistentInstitution.getName(), location.get().getName());
        }
        return new AddInstitutionResponse(false, Constants.FAILURE_INSTITUTION_ID, Constants.EMPTY_RESPONSE_STRING, Constants.EMPTY_RESPONSE_STRING);
    }

    @Override
    public GetAllInstitutionResponse getAllInstitution() {

        List<Institution> institutionList = institutionRepository.findAll();

        if(!institutionList.isEmpty()){
            return new GetAllInstitutionResponse(true, Constants.GET_INSTITUTIONS_SUCCESS_MESSAGE, institutionList);
        }
        return new GetAllInstitutionResponse(false, Constants.GET_INSTITUTIONS_FAILURE_MESSAGE, institutionList);
    }
}
