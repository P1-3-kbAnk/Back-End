package com.kbank.backend.service;

import com.kbank.backend.domain.Hospital;
import com.kbank.backend.domain.HospitalBill;
import com.kbank.backend.domain.PharmacyBill;
import com.kbank.backend.domain.Prescription;
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
    public List<PharmacyBillResponse> getAllBills() {

        List<PharmacyBill> pharmacyBills = pharmacyBillRepository.findAll();
        List<PharmacyBillResponse> pharmacyBillResponses = pharmacyBills.stream()
                .map(PharmacyBillResponse::new)
                .collect(Collectors.toList());

        return pharmacyBillResponses;
    }

    // pharmacyBill 조회 (ID로 조회)
    public PharmacyBillResponse getBillById(Long id) {

        Optional<PharmacyBill> pharmacyBill = pharmacyBillRepository.findById(id);

        return new PharmacyBillResponse(pharmacyBill);
    }

    // 특정 totalPrice로 pharmacyBill 조회
    public List<PharmacyBillResponse> getBillsByTotalPrice (Long totalPrice){
        List<PharmacyBill> pharmacyBills = pharmacyBillRepository.findByTotalPrice(totalPrice);

        List<PharmacyBillResponse> pharmacyBillResponses = pharmacyBills.stream()
                    .map(PharmacyBillResponse::new)
                    .collect(Collectors.toList());

        return pharmacyBillResponses;

    }



    // 특정 날짜에 생성된 pharmacyBill 조회
    public List<PharmacyBillResponse> getBillsByBillYmd(LocalDateTime billYmd) {

        List<PharmacyBill> pharmacyBills = pharmacyBillRepository.findByBillYmd(billYmd);

        List<PharmacyBillResponse> pharmacyBillResponses = pharmacyBills.stream()
                .map(PharmacyBillResponse::new)
                .collect(Collectors.toList());

        return pharmacyBillResponses;
    }

    // 처방전 ID로  pharmacyBill 조회
    public List<PharmacyBillResponse> getBillsByPrescriptionFk(Prescription billPrescriptionFk) {
        List<PharmacyBill> pharmacyBills = pharmacyBillRepository.findByBillPrescriptionFk(billPrescriptionFk);

        List<PharmacyBillResponse> pharmacyBillResponses = pharmacyBills.stream()
                .map(PharmacyBillResponse::new)
                .collect(Collectors.toList());

        return pharmacyBillResponses;
    }


    // pharmacyBill 삭제
    public void deleteBill(Long id) {

        pharmacyBillRepository.deleteById(id);
    }
}
