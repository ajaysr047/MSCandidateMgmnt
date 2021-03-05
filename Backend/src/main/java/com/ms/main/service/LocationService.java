package com.ms.main.service;

import com.ms.main.request.AddLocation;
import com.ms.main.response.AddLocationResponse;
import com.ms.main.response.GetAllLocationResponse;

public interface LocationService {
    AddLocationResponse addLocation(AddLocation location);
    GetAllLocationResponse getAllLocations();
}
