package com.kbank.backend.service;

import com.kbank.backend.domain.MedicineIntake;
import com.kbank.backend.domain.User;
import com.kbank.backend.dto.response.MedicineIntakeResponseDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.MedicineIntakeRepository;
import com.kbank.backend.repository.MedicineRepository;
import com.kbank.backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MedicineIntakeService {

    private final MedicineIntakeRepository medicineIntakeRepository;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;


    // 날짜(2024-10-5)를 받아서 조회
    @Transactional
    public Map<String, Object> getMedicineIntakeByDate(Long userPk, LocalDate date) {


        List<MedicineIntake> medicineIntakeList = medicineIntakeRepository
                .findByMedInkUserAndDay(
                        userRepository.findById(userPk).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER)),
                        date
                );

        Map<String, Object> response = new HashMap<>();
        List<MedicineIntakeResponseDto> medicineIntakeResponseDtoList = new ArrayList<>();

        medicineIntakeList.forEach(e -> {
            medicineIntakeResponseDtoList.add(
                    MedicineIntakeResponseDto
                            .fromEntity(e)
            );
        });

        response.put("medicineIntakeList", medicineIntakeResponseDtoList);

        return response;
    }

    // 복용 여부(EatSt)만 업데이트
    public Boolean updateEatSt(Long userPk, Long medInkPk) {
        User user = userRepository.findById(userPk).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        MedicineIntake medicineIntake = medicineIntakeRepository.findById(medInkPk)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        medicineIntake.updateEatSt();
        medicineIntakeRepository.save(medicineIntake);

        return Boolean.TRUE;
    }

    // 복약 정보 삭제
    public void deleteMedicineIntake(Long id) {

        medicineIntakeRepository.deleteById(id);
    }
}

//복약 여부 id로 조회
//    public MedicineIntakeResponseDto getMedicineIntake(Long id) {
//
//        Optional<MedicineIntake> medicineIntake = medicineIntakeRepository.findById(id);
//
//        return new MedicineIntakeResponseDto(medicineIntake);
//    }
//
//    // 모든 복약 여부 정보 조회
//    public List<MedicineIntakeResponseDto> getAllMedicineIntakes() {
//
//        List<MedicineIntake> medicineIntakes = medicineIntakeRepository.findAll();
//
//        List<MedicineIntakeResponseDto> MedicineIntakeResponses = medicineIntakes.stream()
//                .map(MedicineIntakeResponse::new)  // HospitalBillResponse 생성자로 변환
//                .collect(Collectors.toList());
//
//        return MedicineIntakeResponses;
//    }
//
//    /* 현재 복용 중인 약물 조회 ***/
//    public Map<String, Object> getMedicineIntakeByUser(Long userPk) {
//
//        User user = userRepository.findByUserPk(userPk)
//                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
//        List<MedicineIntake> medicineIntakeList = medicineIntakeRepository.findMedicineIntakeByUserAndDay(user, LocalDate.now());
//        Map<String, Object> result = new HashMap<>();
//
//        result.put("result", medicineIntakeList.stream()
//                .map(MedicineIntakeResponseDto::fromEntity)
//                .toList()
//        );
//
//        return result;
//    }
//
//    // 특정 약물에 대한 복약 여부 조회
//    public List<MedicineIntakeResponse> getMedicineIntakeByMedicine(Medicine medicine) {
//        List<MedicineIntake> medicineIntakes = medicineIntakeRepository.findByMedInkMedicineFk(medicine);
//
//        List<MedicineIntakeResponse> MedicineIntakeResponses = medicineIntakes.stream()
//                .map(MedicineIntakeResponse::new)  // HospitalBillResponse 생성자로 변환
//                .collect(Collectors.toList());
//
//        return MedicineIntakeResponses;
//    }
//
//    // 특정 식사 시간에 복약 여부 조회
//    public List<MedicineIntakeResponse> getMedicineIntakeByMeal(Meal meal) {
//        List<MedicineIntake> medicineIntakes = medicineIntakeRepository.findByMeal(meal);
//
//        List<MedicineIntakeResponse> MedicineIntakeResponses = medicineIntakes.stream()
//                .map(MedicineIntakeResponse::new)  // HospitalBillResponse 생성자로 변환
//                .collect(Collectors.toList());
//        return MedicineIntakeResponses;
//    }
//
//
