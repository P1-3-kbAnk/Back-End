package com.kbank.backend.repository;

import com.kbank.backend.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long>{
    //처방저 create용 메서드 - 김성헌
    List<Doctor> findByDoctorNo(String doctorNo);
}
