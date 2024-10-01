package com.kbank.backend.repository;

import com.kbank.backend.domain.HospitalBill;
import com.kbank.backend.domain.PharmacyBill;
import com.kbank.backend.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PharmacyBillRepository extends JpaRepository<PharmacyBill, Long> {

    Optional<PharmacyBill> findById(long id);

    // 특정 Prescription에 대한 Bill 조회
    Optional<PharmacyBill> findByPharmacyBillPrescriptionPrescriptionPk(Long prescriptionId);
}
