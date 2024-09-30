package com.kbank.backend.service;

import com.kbank.backend.domain.address.Dong;
import com.kbank.backend.domain.Hospital;
import com.kbank.backend.dto.response.HospitalBillResponse;
import com.kbank.backend.dto.response.HospitalResponse;
import com.kbank.backend.repository.HospitalRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    // 특정 ID로 병원 정보 조회
    public HospitalResponse getHospitalById(Long id) {

        Optional<Hospital> hospital = hospitalRepository.findById(id);

        return new HospitalResponse(hospital);
    }

    // 모든 병원 정보 조회
    public List<HospitalResponse> getAllHospitals() {

        List<Hospital> hospitals = hospitalRepository.findAll();
        List<HospitalResponse> hospitalResponses = hospitals.stream()
                .map(HospitalResponse::new)
                .collect(Collectors.toList());

        return hospitalResponses;
    }


    // 특정 동(Dong)에 속한 병원 정보 조회
    public List<HospitalResponse> getHospitalsByDong(Dong dong) {

        List<Hospital> hospitals = hospitalRepository.findByHospitalDong(dong);

        List<HospitalResponse> hospitalResponses = hospitals.stream()
                .map(HospitalResponse::new)
                .collect(Collectors.toList());

        return hospitalResponses;
    }

    // 병원 이름으로 병원 정보 조회
    public List<HospitalResponse> getHospitalsByName(String hospitalNm) {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNm(hospitalNm);
        List<HospitalResponse> hospitalResponses = hospitals.stream()
                .map(HospitalResponse::new)
                .collect(Collectors.toList());

        return hospitalResponses;
    }


}
