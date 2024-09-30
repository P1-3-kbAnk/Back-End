package com.kbank.backend.repository;


import com.kbank.backend.domain.Medicine;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.PrescriptionMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicine, Long> {
    List<PrescriptionMedicine> findByPreMedPrescription(Prescription prescription);

    @Query("SELECT m.medicineNm FROM PrescriptionMedicine pm JOIN pm.preMedPrescription p JOIN pm.preMedMedicine m WHERE p = :prescription")
    List<String> findMedicineByPrescription(@Param("prescription") Prescription prescription);

}
