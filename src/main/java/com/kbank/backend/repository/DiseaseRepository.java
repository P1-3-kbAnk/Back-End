package com.kbank.backend.repository;



import com.kbank.backend.domain.Disease;
import com.kbank.backend.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    Optional<Disease> findById(Long id);

    List<Disease> findAll();

}