package com.kbank.backend.repository;

import com.kbank.backend.domain.PrescriptionMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicine,Long> {
}
