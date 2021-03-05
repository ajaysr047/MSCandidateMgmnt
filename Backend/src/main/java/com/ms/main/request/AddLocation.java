package com.ms.main.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AddLocation {

    @NotEmpty(message = "Location name cannot be empty")
    private String name;
}
