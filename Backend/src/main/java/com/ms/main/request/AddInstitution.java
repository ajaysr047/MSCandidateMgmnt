package com.ms.main.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AddInstitution {

    @NotEmpty(message = "Institution name cannot be empty")
    private String name;

    @NotEmpty(message = "Location name cannot be empty")
    private String locationName;
}
