package com.kbank.backend.service;

import com.kbank.backend.domain.Bill;
import com.kbank.backend.domain.Disease;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    // 질병 생성
    public Disease createDisease(Disease disease) {

        return diseaseRepository.save(disease);
    }

    // 모든 Bill 조회
    public List<Disease> getAllDiseases() {
        return diseaseRepository.findAll();
    }

    // ID로 특정 질병 조회
    public Optional<Disease> getDiseaseById(Long diseasePk) {
        return diseaseRepository.findById(diseasePk);
    }

    // 특정 Prescription에 대한 질병 조회
    public List<Disease> getDiseasesByPrescription(Prescription prescription) {
        return diseaseRepository.findByDiseasePrescriptionFk(prescription);
    }

    // 질병 코드로 질병 조회
    public List<Disease> getDiseasesByCode(String diseaseCd) {
        return diseaseRepository.findByDiseaseCd(diseaseCd);
    }

    // 질병 업데이트
    public Disease updateDisease(Long diseasePk, Disease updatedDisease) {
        return diseaseRepository.findById(diseasePk)
                .map(existingDisease -> {
                    existingDisease.setDiseaseCd(updatedDisease.getDiseaseCd());
                    existingDisease.setDiseasePrescriptionFk(updatedDisease.getDiseasePrescriptionFk());
                    return diseaseRepository.save(existingDisease);
                }).orElseThrow(() -> new IllegalArgumentException("Disease not found with id: " + diseasePk));
    }

    // 질병 삭제
    public void deleteDisease(Long diseasePk) {
        diseaseRepository.deleteById(diseasePk);
    }
}
