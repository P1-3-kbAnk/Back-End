package com.kbank.backend.repository;



import com.kbank.backend.domain.Disease;
import com.kbank.backend.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    Optional<Disease> findById(Long id);

    List<Disease> findAll();

    // 특정 Prescription에 대한 질병 조회
    List<Disease> findByDiseasePrescriptionFk(Prescription prescription);

    // 질병 코드로 질병 조회
    List<Disease> findByDiseaseCd(String diseaseCd);
}