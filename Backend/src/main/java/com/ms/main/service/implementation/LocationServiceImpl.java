package com.ms.main.service.implementation;

import com.ms.main.constants.Constants;
import com.ms.main.entity.Location;
import com.ms.main.repository.LocationRepository;
import com.ms.main.request.AddLocation;
import com.ms.main.response.AddLocationResponse;
import com.ms.main.response.GetAllLocationResponse;
import com.ms.main.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public AddLocationResponse addLocation(AddLocation location) {

        if(locationRepository.findByName(location.getName()).isEmpty()){
            Location persistentLocation = new Location(location.getName());
            Location savedLocation =  locationRepository.save(persistentLocation);
            return new AddLocationResponse(true, savedLocation.getLocationId(), savedLocation.getName(), Constants.LOCATION_ADD_SUCCESS_MESSAGE);
        }
        return new AddLocationResponse(false, Constants.FAILURE_LOCATION_ID, Constants.EMPTY_RESPONSE_STRING, Constants.LOCATION_DUPLICATE_MESSAGE);
    }

    @Override
    public GetAllLocationResponse getAllLocations() {

        List<Location> locationList = locationRepository.findAll();

        if(!locationList.isEmpty()){

            return new GetAllLocationResponse(true, Constants.GET_LOCATIONS_SUCCESS_MESSAGE, locationList);
        }
        return new GetAllLocationResponse(false, Constants.GET_LOCATIONS_FAILURE_MESSAGE, locationList);
    }
}
