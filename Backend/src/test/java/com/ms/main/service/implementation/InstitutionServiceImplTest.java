package com.ms.main.service.implementation;

import com.ms.main.entity.Institution;
import com.ms.main.entity.Location;
import com.ms.main.repository.InstitutionRepository;
import com.ms.main.repository.LocationRepository;
import com.ms.main.request.AddInstitution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InstitutionServiceImplTest {

    @InjectMocks
    InstitutionServiceImpl institutionService;

    @Mock
    InstitutionRepository institutionRepository;

    @Mock
    LocationRepository locationRepository;

    @Test
    void addInstitution() {
        Optional<Location> optionalLocation = Optional.of(new Location("Test Location"));
        Mockito.when(institutionRepository.save(Mockito.any(Institution.class))).thenReturn(new Institution());

        Assertions.assertFalse(institutionService.addInstitution(new AddInstitution()).isSuccess());

        Mockito.when(locationRepository.findByName(Mockito.any())).thenReturn(optionalLocation);
        Assertions.assertTrue(institutionService.addInstitution(new AddInstitution()).isSuccess());
    }

    @Test
    void getAllInstitution() {
        List<Institution> institutionList = new ArrayList<>();
        institutionList.add(new Institution());

        Mockito.when(institutionRepository.findAll()).thenReturn(institutionList);

        Assertions.assertTrue(institutionService.getAllInstitution().isSuccess());

        Mockito.when(institutionRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertFalse(institutionService.getAllInstitution().isSuccess());
    }
}