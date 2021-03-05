package com.ms.main.repository;

import com.ms.main.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institution, Integer> {
}
