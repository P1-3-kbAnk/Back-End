package com.kbank.backend.repository;

import com.kbank.backend.domain.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    Optional<Pharmacy> findByPharmacyPk(Long pharmacyPk);
    Optional<Pharmacy> findByPharmacyNm(String pharmacyName);
}
