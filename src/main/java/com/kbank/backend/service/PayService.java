package com.kbank.backend.service;

import com.kbank.backend.domain.Medicine;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.User;
import com.kbank.backend.dto.response.PayResponseDto;
import com.kbank.backend.dto.response.UserResponseDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.MedicineRepository;
import com.kbank.backend.repository.PrescriptionMedicineRepository;
import com.kbank.backend.repository.PrescriptionRepository;
import com.kbank.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PayService {

    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;

    @Transactional
    public PayResponseDto payment(Long prescriptionId) {

        //처방전 ID로 처방전 찾기
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        //처방전 id 로 사용자 찾기
        User user = userRepository.findById(prescriptionId)
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_USER));
        //처방전에 있는 모든 약 찾기
        List<Medicine> medicines = prescriptionMedicineRepository.findMedicineByPrescription(prescription);
                //medicineRepository.findAllByPrescriptionId(prescriptionId);

        long totalPrice = medicines.stream()
                .mapToLong(Medicine::getPrice)
                .sum();

        long newAccount = user.getAccount() - totalPrice;

        user.setAccount(newAccount);
        userRepository.save(user);

        return PayResponseDto.toEntity(newAccount,totalPrice);
    }




}
