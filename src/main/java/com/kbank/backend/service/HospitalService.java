package com.kbank.backend.service;

import com.kbank.backend.domain.address.Dong;
import com.kbank.backend.domain.Hospital;
import com.kbank.backend.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    // 병원 생성
    public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    // 모든 병원 조회
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    // 특정 ID로 병원 조회
    public Optional<Hospital> getHospitalById(Long hospitalPk) {
        return hospitalRepository.findById(hospitalPk);
    }

    // 특정 동(Dong)에 속한 병원 조회
    public List<Hospital> getHospitalsByDong(Dong dong) {
        return hospitalRepository.findByHospitalDongFk(dong);
    }

    // 병원 이름으로 병원 조회
    public List<Hospital> getHospitalsByName(String hospitalNm) {
        return hospitalRepository.findByHospitalNm(hospitalNm);
    }

    // 전화번호로 병원 조회
    public List<Hospital> getHospitalsByPhone(String phoneNo) {
        return hospitalRepository.findByPhoneNo(phoneNo);
    }

    // 병원 업데이트
    public Hospital updateHospital(Long hospitalPk, Hospital updatedHospital) {
        return hospitalRepository.findById(hospitalPk)
                .map(existingHospital -> {
                    existingHospital.setHospitalNm(updatedHospital.getHospitalNm());
                    existingHospital.setPhoneNo(updatedHospital.getPhoneNo());
                    existingHospital.setHospitalNo(updatedHospital.getHospitalNo());
                    existingHospital.setFaxNo(updatedHospital.getFaxNo());
                    existingHospital.setHospitalDongFk(updatedHospital.getHospitalDongFk());
                    return hospitalRepository.save(existingHospital);
                }).orElseThrow(() -> new IllegalArgumentException("Hospital not found with id: " + hospitalPk));
    }

    // 특정 ID로 병원 삭제
    public void deleteHospital(Long hospitalPk) {

        hospitalRepository.deleteById(hospitalPk);
    }
}
