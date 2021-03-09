package com.ms.main.repository;

import com.ms.main.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
    void deleteAllByCandidateId(Integer id);
}
