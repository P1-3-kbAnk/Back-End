package com.kbank.backend.service;

import com.kbank.backend.domain.*;
import com.kbank.backend.dto.JwtTokenDto;
import com.kbank.backend.dto.request.ChemistRequestDto;
import com.kbank.backend.dto.request.DoctorRequestDto;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.enumerate.Role;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.*;
import com.kbank.backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final AuthUserRepository authUserRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final ChemistRepository chemistRepository;
    private final HospitalRepository hospitalRepository;
    private final PharmacyRepository pharmacyRepository;
    private final JwtUtil jwtUtil;

    public JwtTokenDto userRegister(Long userId, UserRequestDto userRequestDto) {
        AuthUser authUser = authUserRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        User newUser = User.builder()
                .authUser(authUser)
                .userNm(userRequestDto.getUserNm())
                .phoneNo(userRequestDto.getPhoneNo())
                .gender(userRequestDto.getGender())
                .firstNo(userRequestDto.getFirstNo())
                .lastNo(userRequestDto.getLastNo())
                .bankNm(userRequestDto.getBankNm())
                .accountNo(userRequestDto.getAccountNo())
                .accountPw(userRequestDto.getAccountPw())
                .build();

        userRepository.save(newUser);
        authUser.setRole(Role.USER);

        return jwtUtil.generateTokens(authUser.getAuthUserPk(), authUser.getRole());
    }

    public JwtTokenDto doctorRegister(Long doctorId, DoctorRequestDto doctorRequestDto) {
        AuthUser authUser = authUserRepository.findById(doctorId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Hospital hospital = hospitalRepository.findById(doctorRequestDto.getHospitalPk())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_HOSPITAL));
        Doctor newDoctor = Doctor.builder()
                .authUser(authUser)
                .doctorNm(doctorRequestDto.getDoctorNm())
                .tp(doctorRequestDto.getTp())
                .doctorNo(doctorRequestDto.getDoctorNo())
                .phoneNo(doctorRequestDto.getPhoneNo())
                .doctorHospital(hospital)
                .gender(doctorRequestDto.getGender())
                .build();

        doctorRepository.save(newDoctor);
        authUser.setRole(Role.DOCTOR);

        return jwtUtil.generateTokens(authUser.getAuthUserPk(), authUser.getRole());
    }

    public JwtTokenDto chemistRegister(Long chemistId, ChemistRequestDto chemistRequestDto) {
        AuthUser authUser = authUserRepository.findById(chemistId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Pharmacy pharmacy = pharmacyRepository.findById(chemistRequestDto.getPharmacyPk())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_HOSPITAL));
        Chemist newChemist = Chemist.builder()
                .authUser(authUser)
                .chemistNo(chemistRequestDto.getChemistNo())
                .chemistNm(chemistRequestDto.getChemistNm())
                .phoneNo(chemistRequestDto.getPhoneNo())
                .chemistPharmacy(pharmacy)
                .gender(chemistRequestDto.getGender())
                .build();

        chemistRepository.save(newChemist);
        authUser.setRole(Role.CHEMIST);

        return jwtUtil.generateTokens(authUser.getAuthUserPk(), authUser.getRole());
    }

}