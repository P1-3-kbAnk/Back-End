package com.kbank.backend.repository;


import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.Report;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findById(Long id);
    Optional<Report> findByReportPrescription(Prescription prescription);

}
