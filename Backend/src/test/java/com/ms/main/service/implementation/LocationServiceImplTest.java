package com.ms.main.service.implementation;

import com.ms.main.entity.Location;
import com.ms.main.repository.LocationRepository;
import com.ms.main.request.AddLocation;
import com.ms.main.response.AddLocationResponse;
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
class LocationServiceImplTest {

    @InjectMocks
    LocationServiceImpl locationService;

    @Mock
    LocationRepository locationRepository;

    @Test
    void addLocationTest() {
        AddLocationResponse response = new AddLocationResponse();
        response.setSuccess(true);
        AddLocation location = new AddLocation();

        Optional<Location> optionalLocation = Optional.of(new Location("Test Location"));

        Mockito.when(locationRepository.findByName(Mockito.any())).thenReturn(optionalLocation);
        Mockito.when(locationRepository.save(Mockito.any(Location.class))).thenReturn(new Location());

        Assertions.assertFalse(locationService.addLocation(location).isSuccess());

        optionalLocation = Optional.empty();
        Mockito.when(locationRepository.findByName(Mockito.any())).thenReturn(optionalLocation);
        Assertions.assertTrue(locationService.addLocation(location).isSuccess());
    }

    @Test
    void getAllLocationsTest() {
        List<Location> testLocationList = new ArrayList<>();
        testLocationList.add(new Location());

        Mockito.when(locationRepository.findAll()).thenReturn(new ArrayList<>());

        Assertions.assertFalse(locationService.getAllLocations().isSuccess());

        Mockito.when(locationRepository.findAll()).thenReturn(testLocationList);

        Assertions.assertTrue(locationService.getAllLocations().isSuccess());
    }
}