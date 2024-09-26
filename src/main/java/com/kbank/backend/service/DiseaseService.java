package com.kbank.backend.service;


import com.kbank.backend.domain.Disease;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.repository.DiseaseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    // 질병 정보 생성
    public Disease createDisease(Disease disease) {

        return diseaseRepository.save(disease);
    }

    // 모든 질병 정보 조회
    public List<Disease> getAllDiseases() {

        return diseaseRepository.findAll();
    }

    // ID로 특정 질병 정보 조회
    public Optional<Disease> getDiseaseById(Long diseasePk) {

        return diseaseRepository.findById(diseasePk);
    }

    // 처방전 ID 로  질병 정보 조회
    public List<Disease> getDiseasesByPrescription(Prescription prescription) {
        return diseaseRepository.findByDiseasePrescriptionFk(prescription);
    }

    // 질병 코드로 질병 정보 조회
    public List<Disease> getDiseasesByCode(String diseaseCd) {

        return diseaseRepository.findByDiseaseCd(diseaseCd);
    }


    // 질병 삭제
    public void deleteDisease(Long diseasePk) {
        diseaseRepository.deleteById(diseasePk);
    }
}
