package com.kbank.backend.service;

import com.kbank.backend.domain.address.Dong;
import com.kbank.backend.domain.Hospital;
import com.kbank.backend.repository.HospitalRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    // 병원 생성
    public Hospital createHospital(Hospital hospital) {

        return hospitalRepository.save(hospital);
    }

    // 모든 병원 정보 조회
    public List<Hospital> getAllHospitals() {

        return hospitalRepository.findAll();
    }

    // 특정 ID로 병원 정보 조회
    public Optional<Hospital> getHospitalById(Long hospitalPk) {

        return hospitalRepository.findById(hospitalPk);
    }

    // 특정 동(Dong)에 속한 병원 정보 조회
    public List<Hospital> getHospitalsByDong(Dong dong) {

        return hospitalRepository.findByHospitalDongFk(dong);
    }

    // 병원 이름으로 병원 정보 조회
    public List<Hospital> getHospitalsByName(String hospitalNm) {
        return hospitalRepository.findByHospitalNm(hospitalNm);
    }


    // 특정 ID로 병원 정보 삭제
    public void deleteHospital(Long hospitalPk) {

        hospitalRepository.deleteById(hospitalPk);
    }
}
