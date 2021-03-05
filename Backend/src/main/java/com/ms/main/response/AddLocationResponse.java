package com.ms.main.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddLocationResponse {

    private boolean isSuccess;

    private Integer locationId;

    private String name;

    private String message;

    public AddLocationResponse(boolean isSuccess, Integer locationId, String name, String message) {
        this.isSuccess = isSuccess;
        this.locationId = locationId;
        this.name = name;
        this.message = message;
    }

    public AddLocationResponse() {
    }
}
