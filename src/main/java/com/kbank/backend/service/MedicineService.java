package com.kbank.backend.service;

import com.kbank.backend.domain.Medicine;
import com.kbank.backend.dto.response.MedicineResponseDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.MedicineRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineResponseDto medicineDetail(Long userPk, Long medicinePk) {

        Medicine medicine = medicineRepository.findByMedicinePk(medicinePk)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MEDICINE));

        return MedicineResponseDto.toEntity(medicine);
    }
}