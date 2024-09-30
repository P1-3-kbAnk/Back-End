package com.kbank.backend.service;

import com.kbank.backend.domain.Hospital;
import com.kbank.backend.domain.HospitalBill;
import com.kbank.backend.domain.PharmacyBill;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.dto.response.HospitalBillResponse;
import com.kbank.backend.dto.response.PharmacyBillResponse;
import com.kbank.backend.repository.HospitalBillRepository;
import com.kbank.backend.repository.PharmacyBillRepository;
import com.kbank.backend.repository.PrescriptionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PharmacyBillService {

    private final PharmacyBillRepository pharmacyBillRepository;

    //생성 생략 -> 처방전에서 생성

    // 모든 pharmacyBill 조회
    public List<PharmacyBillResponse> getAllBills(Long userid) {

        List<PharmacyBill> pharmacyBills = pharmacyBillRepository.findAll();

        List<PharmacyBillResponse> pharmacyBillList = pharmacyBills.stream()
                .map(PharmacyBillResponse::new)
                .collect(Collectors.toList());

        return pharmacyBillList;
    }

    // 처방전 ID로  pharmacyBill 조회
    public PharmacyBillResponse getBillByPrescriptionFk(Long prescriptionId,Long userid) {
        Optional<PharmacyBill> pharmacyBill = pharmacyBillRepository.findByPharmacyBillPrescription_PrescriptionPk(prescriptionId);

        return new PharmacyBillResponse(pharmacyBill);
    }

    // pharmacyBill 삭제
    public void deleteBill(Long id,Long userid) {
        pharmacyBillRepository.deleteById(id);
    }
}
