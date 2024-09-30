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
    public List<HospitalBillResponse> getAllBills() {

        List<HospitalBill> hospitalBills = hospitalBillRepository.findAll();
        List<HospitalBillResponse> BillResponses = hospitalBills.stream()
                .map(HospitalBillResponse::new)  // HospitalBillResponse 생성자로 변환
                .collect(Collectors.toList());


        return BillResponses;
    }

    // 특정 ID로 hospitalBill 1개 조회
    public HospitalBillResponse getBillById(Long id) {

        Optional<HospitalBill> hospitalBill = hospitalBillRepository.findById(id);

        return new HospitalBillResponse(hospitalBill);
    }

    // 특정 totalPrice로 모든 hospitalBill 조회
    public List<HospitalBillResponse> getBillsByTotalPrice(Long totalPrice) {

        List<HospitalBill> hospitalBills = hospitalBillRepository.findByTotalPrice(totalPrice);

        List<HospitalBillResponse> BillResponses = hospitalBills.stream()
                .map(HospitalBillResponse::new)  // HospitalBillResponse 생성자로 변환
                .collect(Collectors.toList());

        return BillResponses;
    }

    // 특정 날짜에 생성된 모든 hospitalBill 조회
    public List<HospitalBillResponse> getBillsByBillYmd(LocalDateTime billYmd) {

        List<HospitalBill> hospitalBills = hospitalBillRepository.findByCreateYmd(billYmd);

        List<HospitalBillResponse> BillResponses = hospitalBills.stream()
                .map(HospitalBillResponse::new)  // HospitalBillResponse 생성자로 변환
                .collect(Collectors.toList());

        return BillResponses;
    }

    // 처방전 ID로 모든 hospitalBill 조회
    public List<HospitalBillResponse> getBillsByPrescriptionFk(Prescription prescription) {

        List<HospitalBill> hospitalBills = hospitalBillRepository.findByHospitalBillPrescription(prescription);

        List<HospitalBillResponse> BillResponses = hospitalBills.stream()
                .map(HospitalBillResponse::new)  // HospitalBillResponse 생성자로 변환
                .collect(Collectors.toList());

        return BillResponses;
    }


    // hospitalBill 삭제
    public void deleteBill(Long id) {

        hospitalBillRepository.deleteById(id);

    }
}
