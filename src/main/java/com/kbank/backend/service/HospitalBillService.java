package com.kbank.backend.service;

import com.kbank.backend.domain.HospitalBill;
import com.kbank.backend.dto.response.HospitalBillResponseDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.HospitalBillRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalBillService {

    private final HospitalBillRepository hospitalBillRepository;

    //생성 생략 -> 처방전에서 생성

    // 처방전 ID로 hospitalBill 조회
    @Transactional
    public HospitalBillResponseDto getBillByPrescriptionFk(Long userId,Long prescriptionId) {

        HospitalBill hospitalBill = hospitalBillRepository.findByHospitalBillPrescriptionPrescriptionPk(prescriptionId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        return HospitalBillResponseDto.toEntity(hospitalBill.getHospitalBillHospital(),hospitalBill);
    }

    // hospitalBill 삭제
    public void deleteBill(Long userId,Long id) {

        hospitalBillRepository.deleteById(id);

    }
}

