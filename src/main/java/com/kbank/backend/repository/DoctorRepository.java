package com.kbank.backend.repository;

import com.kbank.backend.domain.Chemist;
import com.kbank.backend.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long>{
}
