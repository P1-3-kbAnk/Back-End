package com.kbank.backend.service;


import com.kbank.backend.domain.Disease;
import com.kbank.backend.domain.HospitalBill;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.dto.response.DiseaseResponse;
import com.kbank.backend.dto.response.HospitalBillResponse;
import com.kbank.backend.repository.DiseaseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    // ID로 특정 질병 정보 조회
    public DiseaseResponse getDiseaseById(Long id) {

        Optional<Disease> disease =diseaseRepository.findById(id);
        return new DiseaseResponse(disease);
    }

    // 모든 질병 정보 조회
    public List<DiseaseResponse> getAllDiseases() {

        List<Disease> diseases = diseaseRepository.findAll();
        List<DiseaseResponse> diseaseResponses = diseases.stream()
                .map(DiseaseResponse::new)  // HospitalBillResponse 생성자로 변환
                .collect(Collectors.toList());

        return diseaseResponses;
    }


    // 처방전 ID 로  질병 정보 조회
    public List<DiseaseResponse> getDiseasesByPrescription(Prescription prescription) {
        List<Disease> diseases = diseaseRepository.findByDiseasePrescription(prescription);
        List<DiseaseResponse> diseaseResponses = diseases.stream()
                .map(DiseaseResponse::new)  // HospitalBillResponse 생성자로 변환
                .collect(Collectors.toList());

        return diseaseResponses;
    }

    // 질병 코드로 질병 정보 조회
    public List<DiseaseResponse> getDiseasesByCode(String diseaseCd) {

        List<Disease> diseases =  diseaseRepository.findByDiseaseCd(diseaseCd);
        List<DiseaseResponse> diseaseResponses = diseases.stream()
                .map(DiseaseResponse::new)  // HospitalBillResponse 생성자로 변환
                .collect(Collectors.toList());

        return diseaseResponses;
    }

}
