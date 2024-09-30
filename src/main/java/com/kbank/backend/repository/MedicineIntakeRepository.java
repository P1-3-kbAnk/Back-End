package com.kbank.backend.repository;

import com.kbank.backend.domain.Medicine;
import com.kbank.backend.domain.MedicineIntake;
import com.kbank.backend.domain.User;
import com.kbank.backend.enumerate.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineIntakeRepository extends JpaRepository<MedicineIntake, Long> {

    Optional<MedicineIntake> findById(long id);

    List<MedicineIntake> findAll();

    // 특정 사용자에 대한 복약 정보 조회
    List<MedicineIntake> findByMedInkUser(User user);

    // 특정 약물에 대한 복약 정보 조회
    List<MedicineIntake> findByMedInkMedicine(Medicine medicine);

    // 특정 식사 시간에 복약 여부 조회
    List<MedicineIntake> findByMeal(Meal meal);
}