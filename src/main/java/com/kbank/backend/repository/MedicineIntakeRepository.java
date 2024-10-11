package com.kbank.backend.repository;

import com.kbank.backend.domain.MedicineIntake;
import com.kbank.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineIntakeRepository extends JpaRepository<MedicineIntake, Long> {

    Optional<MedicineIntake> findById(long id);

    List<MedicineIntake> findAll();

    List<MedicineIntake> findByMedInkUserAndDay(User user, LocalDate day);



}