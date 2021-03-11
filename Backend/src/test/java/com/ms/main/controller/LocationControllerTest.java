package com.ms.main.controller;

import com.ms.main.request.AddLocation;
import com.ms.main.response.AddLocationResponse;
import com.ms.main.response.GetAllLocationResponse;
import com.ms.main.service.LocationService;
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
class LocationControllerTest {

    @InjectMocks
    LocationController locationController;

    @Mock
    LocationService locationService;

    @Test
    void addLocationTest() {
        AddLocationResponse response =  new AddLocationResponse();
        response.setSuccess(true);

        AddLocation location = new AddLocation();

        Mockito.when(locationService.addLocation(Mockito.any(AddLocation.class))).thenReturn(response);

        Assertions.assertTrue(Objects.requireNonNull(locationController.addLocation(location).getBody()).isSuccess());

        response.setSuccess(false);
        Assertions.assertFalse(Objects.requireNonNull(locationController.addLocation(location).getBody()).isSuccess());
    }

    @Test
    void getAllLocationsTest() {
        GetAllLocationResponse response = new GetAllLocationResponse();
        response.setSuccess(true);

        Mockito.when(locationService.getAllLocations()).thenReturn(response);

        Assertions.assertTrue(Objects.requireNonNull(locationController.getAllLocations().getBody()).isSuccess());

        response.setSuccess(false);
        Assertions.assertFalse(Objects.requireNonNull(locationController.getAllLocations().getBody()).isSuccess());
    }
}