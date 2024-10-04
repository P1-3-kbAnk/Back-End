package com.kbank.backend.service;

import com.kbank.backend.domain.*;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.*;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PayService {

    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;
    private final PharmacyBillRepository pharmacyBillRepository;

    @Transactional
    public Map<?,?> payment(Long userId, Long prescriptionId) {

        //처방전 ID로 처방전 찾기
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        //처방전 id 로 사용자 찾기
        User user = userRepository.findById(prescriptionId)
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_USER));
        //처방전에 있는 모든 약 찾기
        List<Medicine> medicines = prescriptionMedicineRepository.findMedicineByPrescription(prescription);



        long totalPrice = medicines.stream()
                .mapToLong(Medicine::getPrice)
                .sum();

        //계좌 업데이트
        long newAccount = user.getAccount() - totalPrice;

        // 계좌가 부족한 경우 예외 발생
        if (newAccount < 0) {
            throw new CommonException(ErrorCode.INSUFFICIENT_FUNDS); // 새로운 에러코드 정의 필요
        }
        user.setAccount(newAccount); //dynamic update

        //약국 영수증 생성
        PharmacyBill pharmacyBill = PharmacyBill.builder()
                .totalPrice(totalPrice)
                .pharmacyBillPrescription(prescription)
                .pharmacyBillPharmacy(prescription.getPreChemist().getChemistPharmacy())
                .build();

        pharmacyBillRepository.save(pharmacyBill);

        Map<String, String> result = new HashMap<>();
        result.put("totalPrice", String.valueOf(totalPrice));
        result.put("Account", String.valueOf(newAccount));


        return result;
    }




}
