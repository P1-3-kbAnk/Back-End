package com.kbank.backend.repository;



import com.kbank.backend.domain.disease.Disease;
import com.kbank.backend.domain.prescription.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    // 특정 Prescription에 대한 질병 조회
    List<Disease> findByDiseasePrescriptionFk(Prescription prescription);

    // 질병 코드로 질병 조회
    List<Disease> findByDiseaseCd(String diseaseCd);
}