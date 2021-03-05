package com.ms.main.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skillUid;

    @Column
    private Integer candidateId;

    @Column
    private String skillName;

    public Skill(Integer candidateId, String skillName) {
        this.candidateId = candidateId;
        this.skillName = skillName;
    }
}
