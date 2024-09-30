package com.kbank.backend.repository;


import com.kbank.backend.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long>{
    //조제여부 false인 처방전 목록 조회
    List<Prescription> findByPrescriptionStFalse();

    Optional<Prescription> findByPrescriptionNo(int prescriptionNo);
}
