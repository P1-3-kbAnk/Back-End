package com.kbank.backend.repository;

import com.kbank.backend.domain.Doctor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long>{


    @EntityGraph(attributePaths = "doctorHospital")
    @Query("SELECT d FROM Doctor d WHERE d.doctorPk = :doctorPk")
    Optional<Doctor> findByIdWithHospital(@Param("doctorPk") Long doctorPk);

    Optional<Doctor> findDoctorByDoctorPk(Long doctorPk);
}
