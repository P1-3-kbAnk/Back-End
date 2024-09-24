package com.kbank.backend.service.prescription;


import com.kbank.backend.domain.Prescription;

import com.kbank.backend.dto.request.PrescriptionRequest;
import com.kbank.backend.repository.PrescriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface PrescriptionService {
    //특정 id로 처방전 조회
    Optional<Prescription> findById(Long id);
    //모든 처방전 조회
    List<Prescription> findAll();
    //조제 여부가 false인 처방전 목록 조회
    List<Prescription> findNotRecived();

    Prescription createPrescription(PrescriptionRequest dto);
}
