package com.kbank.backend.repository;

import com.kbank.backend.domain.Medicine;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.PrescriptionMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicine,Long> {
    List<PrescriptionMedicine> findByPreMedPrescription(Prescription prescription);

    @Query("SELECT m FROM PrescriptionMedicine pm JOIN pm.preMedPrescription p JOIN pm.preMedMedicine m WHERE p = :prescription")
    List<Medicine> findMedicineByPrescription(@Param("prescription") Prescription prescription);
}
