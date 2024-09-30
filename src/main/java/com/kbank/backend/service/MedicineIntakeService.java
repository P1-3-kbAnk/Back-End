package com.kbank.backend.service;

import com.kbank.backend.domain.Medicine;
import com.kbank.backend.domain.MedicineIntake;
import com.kbank.backend.domain.User;
import com.kbank.backend.dto.response.HospitalBillResponse;
import com.kbank.backend.dto.response.MedicineIntakeResponse;
import com.kbank.backend.enumerate.Meal;
import com.kbank.backend.repository.MedicineIntakeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MedicineIntakeService {

    private final MedicineIntakeRepository medicineIntakeRepository;


    //복약 여부 id로 조회
    public MedicineIntakeResponse getMedicineIntake(Long id) {

        Optional<MedicineIntake> medicineIntake = medicineIntakeRepository.findById(id);

        return new MedicineIntakeResponse(medicineIntake);
    }

    // 모든 복약 여부 정보 조회
    public List<MedicineIntakeResponse> getAllMedicineIntakes() {

        List<MedicineIntake> medicineIntakes = medicineIntakeRepository.findAll();

        List<MedicineIntakeResponse> MedicineIntakeResponses = medicineIntakes.stream()
                .map(MedicineIntakeResponse::new)  // HospitalBillResponse 생성자로 변환
                .collect(Collectors.toList());

        return MedicineIntakeResponses;
    }

    // 특정 사용자에 대한 복약 여부 조회
    public List<MedicineIntakeResponse> getMedicineIntakeByUser(User user) {
        List<MedicineIntake> medicineIntakes = medicineIntakeRepository.findByMedInkUser(user);

        List<MedicineIntakeResponse> MedicineIntakeResponses = medicineIntakes.stream()
                .map(MedicineIntakeResponse::new)  // HospitalBillResponse 생성자로 변환
                .collect(Collectors.toList());

        return MedicineIntakeResponses;
    }

    // 특정 약물에 대한 복약 여부 조회
    public List<MedicineIntakeResponse> getMedicineIntakeByMedicine(Medicine medicine) {
        List<MedicineIntake> medicineIntakes = medicineIntakeRepository.findByMedInkMedicine(medicine);

        List<MedicineIntakeResponse> MedicineIntakeResponses = medicineIntakes.stream()
                .map(MedicineIntakeResponse::new)  // HospitalBillResponse 생성자로 변환
                .collect(Collectors.toList());

        return MedicineIntakeResponses;
    }

    // 특정 식사 시간에 복약 여부 조회
    public List<MedicineIntakeResponse> getMedicineIntakeByMeal(Meal meal) {
        List<MedicineIntake> medicineIntakes = medicineIntakeRepository.findByMeal(meal);

        List<MedicineIntakeResponse> MedicineIntakeResponses = medicineIntakes.stream()
                .map(MedicineIntakeResponse::new)  // HospitalBillResponse 생성자로 변환
                .collect(Collectors.toList());
        return MedicineIntakeResponses;
    }


    // 복용 여부(EatSt)만 업데이트
//    public Boolean updateEatSt(Long medInkPk) {
//
//        MedicineIntake medicineIntake = medicineIntakeRepository
//                .findById(medInkPk)
//                .orElseThrow(
//                        () -> new IllegalArgumentException("MedicineIntake not found with id: " + medInkPk));
//        medicineIntake.(true);
//        medicineIntakeRepository.save(medicineIntake);
//
//        return Boolean.TRUE;
//    }

    // 복약 정보 삭제
    public void deleteMedicineIntake(Long id) {

        medicineIntakeRepository.deleteById(id);
    }
}
