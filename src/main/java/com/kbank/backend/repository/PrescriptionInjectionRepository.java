package com.kbank.backend.repository;

import com.kbank.backend.domain.PrescriptionInjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionInjectionRepository extends JpaRepository<PrescriptionInjection,Long> {

    @Query("SELECT pi FROM PrescriptionInjection pi WHERE pi.preInjPrescription.prescriptionPk = :prescriptionId")
    List<PrescriptionInjection> findInjectionsByPrescriptionId(@Param("prescriptionId") Long prescriptionId);

}
