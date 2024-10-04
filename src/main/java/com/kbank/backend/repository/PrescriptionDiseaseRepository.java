package com.kbank.backend.repository;

import com.kbank.backend.domain.Disease;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.PrescriptionDisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionDiseaseRepository extends JpaRepository<PrescriptionDisease, Long> {

    @Query("SELECT pd FROM PrescriptionDisease pd WHERE pd.preDisPrescription.prescriptionPk = :prescriptionId")
    List<PrescriptionDisease> findDiseasesByPrescriptionId(@Param("prescriptionId") Long prescriptionId);

    List<Disease> findByPreDisPrescription(Prescription prescription);

}