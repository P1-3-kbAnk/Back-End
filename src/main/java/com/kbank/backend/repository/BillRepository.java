package com.kbank.backend.repository;

import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    // 특정 totalPrice로 Bill 조회
    List<Bill> findByTotalPrice(long totalPrice);

    // 특정 날짜에 생성된 Bill 조회
    List<Bill> findByBillYmd(LocalDateTime billYmd);

    // 특정 Prescription에 대한 Bill 조회
    List<Bill> findByBillPrescriptionFk(Prescription billPrescriptionFk);
}