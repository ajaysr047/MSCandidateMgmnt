package com.ms.main.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer candidateId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String description;

    @Column
    private String feedback;

    @OneToMany(mappedBy = "candidateId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Skill> skillSet = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "joining_location_id")
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_user_id")
    private User user;

    @Column
    boolean isActive;

    public Candidate(Map<String, String> consolidatedParam, Location location, Institution institution, User user, boolean isActive) {

        this.name = consolidatedParam.get("name");
        this.email = consolidatedParam.get("email");
        this.phoneNumber = consolidatedParam.get("phone");
        this.description = consolidatedParam.get("description");
        this.feedback = consolidatedParam.get("feedback");
        this.location = location;
        this.institution = institution;
        this.user = user;
        this.isActive = isActive;
    }
}
