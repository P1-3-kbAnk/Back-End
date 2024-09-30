package com.kbank.backend.repository;

import com.kbank.backend.domain.Chemist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChemistRepository extends JpaRepository<Chemist,Long> {
    // 처방전 create용 메서드- 김성헌
    List<Chemist> findByChemistNm(String chemistNm);
}
