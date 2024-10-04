package com.kbank.backend.repository;


import com.kbank.backend.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long>{
    //조제여부 false인 처방전 목록 조회

    @Query(value = "SELECT * FROM prescription_tb p WHERE p.pre_user_fk = :userId " +
            "AND p.prescription_st IS FALSE", nativeQuery = true)
    List<Prescription> findByUserPkAndPreStFalse(@Param("userId") Long userId);

    Optional<Prescription> findByPrescriptionNo(int prescriptionNo);

    List<Prescription> findByPreChemist_ChemistPkAndPrescriptionStTrue(Long chemistId);
}
