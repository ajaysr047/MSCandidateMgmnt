package com.ms.main.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddInstitutionResponse {

    private boolean isSuccess;

    private Integer institutionId;

    private String name;

    private String locationName;

    public AddInstitutionResponse() {
    }

    public AddInstitutionResponse(boolean isSuccess, Integer institutionId, String name, String locationName) {
        this.isSuccess = isSuccess;
        this.institutionId = institutionId;
        this.name = name;
        this.locationName = locationName;
    }
}
