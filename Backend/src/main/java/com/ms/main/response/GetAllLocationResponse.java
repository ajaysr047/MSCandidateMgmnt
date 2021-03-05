package com.ms.main.response;


import com.ms.main.entity.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllLocationResponse {

    private boolean isSuccess;

    private String message;

    private List<Location> locationList = new ArrayList<>();

    public GetAllLocationResponse(boolean isSuccess, String message, List<Location> locationList) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.locationList = locationList;
    }

    public GetAllLocationResponse() {
    }
}
