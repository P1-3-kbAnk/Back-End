package com.kbank.backend.service;

import com.kbank.backend.domain.Pharmacy;
import com.kbank.backend.domain.PharmacyBill;
import com.kbank.backend.dto.response.PharmacyBillResponseDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.PharmacyBillRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PharmacyBillService {

    private final PharmacyBillRepository pharmacyBillRepository;

    //생성 생략 -> 처방전에서 생성

    // 처방전 ID로  pharmacyBill 조회
    @Transactional
    public PharmacyBillResponseDto getBillByPrescription(Long userId,Long prescriptionId) {

        PharmacyBill pharmacyBill = pharmacyBillRepository
                .findByPharmacyBillPrescriptionPrescriptionPk(prescriptionId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        String pharmacyNm = pharmacyBill.getPharmacyBillPharmacy().getPharmacyNm();
        LocalDateTime createYmd = pharmacyBill.getCreateYmd();
        Pharmacy pharmacy = pharmacyBill.getPharmacyBillPharmacy();

        return PharmacyBillResponseDto.toEntity(pharmacy,pharmacyBill,prescriptionId,pharmacyNm,createYmd);

    }

    // pharmacyBill 삭제
    public void deleteBill(Long id, long userId) {

        pharmacyBillRepository.deleteById(id);
    }
}

