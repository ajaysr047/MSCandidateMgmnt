package com.ms.main.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class UpdateCandidate {

    @NotEmpty(message = "Candidate email cannot be empty!")
    @Email
    private String email;

    @NotEmpty(message = "Phone number cannot be empty!")
    private String phoneNumber;

    private String description;

    private String feedback;

    @NotEmpty(message = "At least one skill must be added!")
    private Set<String> skillSet;

}