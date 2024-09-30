package com.kbank.backend.service.report;

import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    Optional<Report> findById(Long id);
    Optional<Report> findByPrescription(Prescription prescription);
    List<Report> findAll();
    List<String> getMedicineList(Prescription prescription);
}
