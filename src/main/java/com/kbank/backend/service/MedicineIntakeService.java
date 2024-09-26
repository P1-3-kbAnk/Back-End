package com.kbank.backend.service;

import com.kbank.backend.domain.Medicine;
import com.kbank.backend.domain.MedicineIntake;
import com.kbank.backend.domain.User;
import com.kbank.backend.enumerate.Meal;
import com.kbank.backend.repository.MedicineIntakeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class MedicineIntakeService {

    private final MedicineIntakeRepository medicineIntakeRepository;

    // 복약 여부 생성
    public MedicineIntake createMedicineIntake(MedicineIntake medicineIntake) {
        return medicineIntakeRepository.save(medicineIntake);
    }

    // 모든 복약 여부 조회
    public List<MedicineIntake> getAllMedicineIntakes() {

        return medicineIntakeRepository.findAll();
    }

    // 특정 사용자에 대한 복약 여부 조회
    public List<MedicineIntake> getMedicineIntakeByUser(User user) {
        return medicineIntakeRepository.findByMedInkUserFk(user);
    }

    // 특정 약물에 대한 복약 여부 조회
    public List<MedicineIntake> getMedicineIntakeByMedicine(Medicine medicine) {
        return medicineIntakeRepository.findByMedInkMedicineFk(medicine);
    }

    // 특정 식사 시간에 복약 여부 조회
    public List<MedicineIntake> getMedicineIntakeByMeal(Meal meal) {

        return medicineIntakeRepository.findByMeal(meal);
    }


    // 복용 여부(EatSt)만 업데이트
    public Boolean updateEatSt(Long medInkPk) {

        MedicineIntake medicineIntake = medicineIntakeRepository
                .findById(medInkPk)
                .orElseThrow(
                        () -> new IllegalArgumentException("MedicineIntake not found with id: " + medInkPk));
        medicineIntake.setEatSt(true);
        medicineIntakeRepository.save(medicineIntake);

        return Boolean.TRUE;
    }

    // 복약 정보 삭제
    public void deleteMedicineIntake(Long id) {

        medicineIntakeRepository.deleteById(id);
    }
}
