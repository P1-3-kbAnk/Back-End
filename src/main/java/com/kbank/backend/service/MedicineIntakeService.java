package com.kbank.backend.service;

import com.kbank.backend.domain.MedicineIntake;
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
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MedicineIntakeService {

    private final MedicineIntakeRepository medicineIntakeRepository;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;


    // 날짜(2024-10-5)를 받아서 조회
    public Map<String, Object> getMedicineIntakeByDate(Long userPk, LocalDate date) {


        List<MedicineIntake> medicineIntakeList = medicineIntakeRepository
                .findByMedInkUserAndDay(
                        userRepository.findByUserWithAuthUserId(userPk).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER)),
                        date
                );

        Map<String, Object> response = new HashMap<>();
        List<MedicineIntakeResponseDto> medicineIntakeResponseDtoList = medicineIntakeList.stream()
                .map(MedicineIntakeResponseDto::fromEntity)
                .toList();


        response.put("medicineIntakeList", medicineIntakeResponseDtoList);

        return response;
    }

    // 복용 여부(EatSt)만 업데이트
    public Boolean updateEatSt(Long medInkPk) {
        MedicineIntake medicineIntake = medicineIntakeRepository.findById(medInkPk)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        medicineIntake.updateEatSt();

        return Boolean.TRUE;
    }

    // 복약 정보 삭제
    public void deleteMedicineIntake(Long id) {

        medicineIntakeRepository.deleteById(id);
    }
}