package com.kbank.backend.service;

import com.kbank.backend.domain.Doctor;
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

    public DoctorResponseDto doctorInfo(Long doctorPk) {
        Doctor doctor = doctorRepository.findDoctorByDoctorPk(doctorPk)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_DOCTOR));

        return DoctorResponseDto.fromEntity(doctor);

    }
}
