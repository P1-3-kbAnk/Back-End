package com.kbank.backend.service;

import com.kbank.backend.domain.*;
import com.kbank.backend.dto.JwtTokenDto;
import com.kbank.backend.dto.request.ChemistRequestDto;
import com.kbank.backend.dto.request.DoctorRequestDto;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Role;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.mapper.DongMapper;
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
    private final PharmacyRepository pharmacyRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final ChemistRepository chemistRepository;
    private final DongMapper dongMapper;
    private final JwtUtil jwtUtil;

    public JwtTokenDto createUser(Long userId, UserRequestDto userRequestDto) {
        AuthUser authUser = authUserRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        Gender gender = userRequestDto.getLastNo().startsWith("2") || userRequestDto.getLastNo().startsWith("4") ? Gender.FEMALE : Gender.MALE;

        User newUser = User.builder()
                .userNm(userRequestDto.getUserNm())
                .phoneNo(userRequestDto.getPhoneNo())
                .gender(gender)
                .firstNo(userRequestDto.getFirstNo())
                .lastNo(userRequestDto.getLastNo())
                .bankNm(userRequestDto.getBankNm())
                .accountNo(userRequestDto.getAccountNo())
                .accountPw(userRequestDto.getAccountPw())
                .authUser(authUser)
                .fcmNo(userRequestDto.getFcmNo())
                .build();

        userRepository.save(newUser);
        authUser.updateRole(Role.USER);

        return jwtUtil.generateTokens(authUser.getAuthUserPk(), Role.USER);
    }

    public JwtTokenDto createDoctor(Long userId, DoctorRequestDto doctorRequestDto) {

        AuthUser authUser = authUserRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        Gender gender = doctorRequestDto.getLastNo().startsWith("2") || doctorRequestDto.getLastNo().startsWith("4") ? Gender.FEMALE : Gender.MALE;

        Hospital hospital = hospitalRepository.findByHospitalNm(doctorRequestDto.getHospitalNm())
                .stream().filter(e -> e.getHospitalDong().getDong_nm().equals(doctorRequestDto.getHospitalDong()))
                .findFirst()
                .orElseGet(() -> {
                    Hospital tempHos = Hospital
                            .builder()
                            .hospitalNm(doctorRequestDto.getHospitalNm())
                            .hospitalDong(dongMapper.findByName(doctorRequestDto.getHospitalDong()))
                            .phoneNo(doctorRequestDto.getHospitalPhoneNo())
                            .detailAddress(doctorRequestDto.getHospitalDetailAddress())
                            .build();

                    hospitalRepository.save(tempHos);
                    return tempHos;
                });

        Doctor newDoctor = Doctor.builder()
                .doctorNm(doctorRequestDto.getUserNm())
                .doctorNo(doctorRequestDto.getDoctorNo())
                .doctorHospital(hospital)
                .tp(doctorRequestDto.getTp())
                .gender(gender)
                .phoneNo(doctorRequestDto.getPhoneNo())
                .authUser(authUser)
                .build();

        doctorRepository.save(newDoctor);
        authUser.updateRole(Role.DOCTOR);

        return jwtUtil.generateTokens(authUser.getAuthUserPk(), Role.DOCTOR);
    }

    public JwtTokenDto createChemist(Long userId, ChemistRequestDto chemistRequestDto) {

        AuthUser authUser = authUserRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        Pharmacy pharmacy = pharmacyRepository.findByPharmacyNm(chemistRequestDto.getPharmacyNm())
                .orElseGet(() -> {
                    Pharmacy tempPharmacy = Pharmacy
                            .builder()
                            .pharmacyNm(chemistRequestDto.getPharmacyNm())
                            .pharmacyDong(dongMapper.findByName(chemistRequestDto.getPharmacyDong()))
                            .phoneNo(chemistRequestDto.getPharmacyPhoneNo())
                            .detailAddress(chemistRequestDto.getPharmacyDetailAddress())
                            .build();

                    pharmacyRepository.save(tempPharmacy);
                    return tempPharmacy;
                });

        Gender gender = chemistRequestDto.getLastNo().startsWith("2") || chemistRequestDto.getLastNo().startsWith("4") ? Gender.FEMALE : Gender.MALE;

        Chemist newChemist = Chemist.builder()
                .chemistNm(chemistRequestDto.getUserNm())
                .chemistNo(chemistRequestDto.getChemistNo())
                .phoneNo(chemistRequestDto.getPhoneNo())
                .gender(gender)
                .chemistPharmacy(pharmacy)
                .authUser(authUser)
                .build();


        chemistRepository.save(newChemist);
        authUser.updateRole(Role.CHEMIST);

        return jwtUtil.generateTokens(authUser.getAuthUserPk(), Role.CHEMIST);
    }

}