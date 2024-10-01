package com.kbank.backend.service;

import com.kbank.backend.domain.Doctor;
import com.kbank.backend.domain.Hospital;
import com.kbank.backend.dto.request.DoctorRequestDto;
import com.kbank.backend.dto.response.DoctorResponseDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.DoctorRepository;
import com.kbank.backend.repository.HospitalRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;

    public Boolean createDoctor(DoctorRequestDto doctorRequestDto) {
        // 시큐리티 후 수정
        Hospital hospital = hospitalRepository.findById(doctorRequestDto.getHospitalPk())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_HOSPITAL));
        Doctor newDoctor = Doctor.builder()
                .doctorNm(doctorRequestDto.getDoctorNm())
                .doctorNo(doctorRequestDto.getDoctorNo())
                .doctorHospital(hospital)
                .tp(doctorRequestDto.getTp())
                .gender(doctorRequestDto.getGender())
                .phoneNo(doctorRequestDto.getPhoneNo())
                .build();

        doctorRepository.save(newDoctor);

        return Boolean.TRUE;
    }

    public DoctorResponseDto doctorInfo(Long doctorPk) {
        Doctor doctor = doctorRepository.findDoctorByDoctorPk(doctorPk)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_DOCTOR));

        return DoctorResponseDto.toEntity(doctor);

    }
}
