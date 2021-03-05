package com.ms.main.repository;

import com.ms.main.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    Optional<Candidate> findByEmail(String email);
}
