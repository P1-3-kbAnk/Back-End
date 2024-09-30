package com.kbank.backend.service;

import com.kbank.backend.domain.Hospital;
import com.kbank.backend.domain.HospitalBill;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.dto.response.HospitalBillResponse;
import com.kbank.backend.repository.HospitalBillRepository;
import com.kbank.backend.repository.PrescriptionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalBillService {

    private final HospitalBillRepository hospitalBillRepository;

    //생성 생략 -> 처방전에서 생성

    // 모든 hospitalBill 조회
    public List<HospitalBillResponse> getAllBills(Long userid) {

        List<HospitalBill> hospitalBills = hospitalBillRepository.findAll();
        List<HospitalBillResponse> BillResponses = hospitalBills
                .stream()
                .map(HospitalBillResponse::new)  // HospitalBillResponse 생성자로 변환
                .collect(Collectors.toList());

        return BillResponses;
    }

    // 처방전 ID로 hospitalBill 조회
    public HospitalBillResponse getBillByPrescriptionFk(Long prescriptionId,Long userid) {

        Optional<HospitalBill> hospitalBill = hospitalBillRepository.findByHospitalBillPrescription_PrescriptionPk(prescriptionId);

        return new HospitalBillResponse(hospitalBill);
    }


    // hospitalBill 삭제
    public void deleteBill(Long id,Long userid) {

        hospitalBillRepository.deleteById(id);

    }
}
