package com.ms.main.response;

import com.ms.main.entity.Institution;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllInstitutionResponse {

    private boolean isSuccess;

    private String message;

    private List<Institution> institutionList = new ArrayList<>();

    public GetAllInstitutionResponse() {
    }

    public GetAllInstitutionResponse(boolean isSuccess, String message, List<Institution> institutionList) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.institutionList = institutionList;
    }
}
