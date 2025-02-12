package com.kbank.backend.repository;


import com.kbank.backend.domain.Chemist;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long>{
    //조제여부 false인 처방전 목록 조회

    @EntityGraph(attributePaths = {
            "preDoctor",
            "preDoctor.doctorHospital",
            "preDoctor.doctorHospital.hospitalDong",
            "preDoctor.doctorHospital.hospitalDong.dong_gu_fk",
            "preDoctor.doctorHospital.hospitalDong.dong_gu_fk.gu_si_fk"
    })
    @Query("SELECT p FROM Prescription p JOIN FETCH p.preUser u " +
            "JOIN FETCH u.authUser au " +
            "WHERE au.authUserPk = :authUserId " +
            "AND p.prescriptionSt = FALSE")
    List<Prescription> findByUserFkAndPreStFalse(@Param("authUserId") Long userId);

    @EntityGraph(attributePaths = {"preDoctor", "preDoctor.doctorHospital", "preChemist", "preChemist.chemistPharmacy", "preUser"})
    Optional<Prescription> findPrescriptionByPrescriptionPk(Long prescriptionId);

    @EntityGraph(attributePaths = "preUser")
    Optional<Prescription> findByPrescriptionPk(Long prescriptionPk);
    @EntityGraph(attributePaths = {
            "preDoctor",
            "preDoctor.doctorHospital",
            "preDoctor.doctorHospital.hospitalDong",
            "preDoctor.doctorHospital.hospitalDong.dong_gu_fk",
            "preDoctor.doctorHospital.hospitalDong.dong_gu_fk.gu_si_fk",
            "preChemist",
            "preChemist.chemistPharmacy"
    })
    Page<Prescription> findAllByPreUser(User user, Pageable pageable);

    @EntityGraph(attributePaths = {
            "preDoctor",
            "preDoctor.doctorHospital",
            "preDoctor.doctorHospital.hospitalDong",
            "preDoctor.doctorHospital.hospitalDong.dong_gu_fk",
            "preDoctor.doctorHospital.hospitalDong.dong_gu_fk.gu_si_fk",
            "preChemist",
            "preChemist.chemistPharmacy"
    })
    Page<Prescription> findAllByPreChemist(Chemist chemist, Pageable pageable);

    @Query("SELECT COUNT(p) FROM Prescription p WHERE DATE(p.createYmd) = :searchDate")
    Integer findAllByDate(@Param("searchDate") LocalDate searchDate);


    Optional<Prescription> findByPrescriptionNo(int prescriptionNo);

    List<Prescription> findByPreChemist_ChemistPkAndPrescriptionStTrue(Long chemistId);
}
