package com.ms.main.controller;

import com.ms.main.request.AddLocation;
import com.ms.main.response.AddLocationResponse;
import com.ms.main.response.GetAllLocationResponse;
import com.ms.main.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @PostMapping("/add")
    public ResponseEntity<AddLocationResponse> addLocation(@Valid @RequestBody AddLocation location){

        AddLocationResponse response = locationService.addLocation(location);

        if(response.isSuccess()){

            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<GetAllLocationResponse> getAllLocations(){
        GetAllLocationResponse response = locationService.getAllLocations();
        if(response.isSuccess()){
            return ResponseEntity.ok(response);
        }

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
