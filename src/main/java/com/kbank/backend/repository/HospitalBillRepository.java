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

    List<HospitalBill> findAll();

    // 특정 totalPrice로 Bill 조회
    List<HospitalBill> findByTotalPrice(long totalPrice);

    // 특정 날짜에 생성된 Bill 조회
    List<HospitalBill> findByCreateYmd(LocalDateTime CreateYmd);

    // 특정 Prescription에 대한 Bill 조회
    Optional<HospitalBill> findByHospitalBillPrescriptionPrescriptionPk(Long prescriptionId);

}