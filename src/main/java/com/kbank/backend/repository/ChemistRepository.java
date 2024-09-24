package com.kbank.backend.repository;

import com.kbank.backend.domain.Chemist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemistRepository extends JpaRepository<Chemist,Long> {
}
