package com.kbank.backend.repository;

import com.kbank.backend.domain.HospitalBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalBillRepository extends JpaRepository<HospitalBill, Long> {

    Optional<HospitalBill> findById(long id);

    // 특정 Prescription에 대한 Bill 조회
    Optional<HospitalBill> findByHospitalBillPrescriptionPrescriptionPk(Long prescriptionId);

}
