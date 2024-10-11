package com.kbank.backend.enumerate;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Tp {
    GENERAL_PRACTITIONER("일반의"),   // 일반의
    INTERNIST("내과 의사"),              // 내과 의사
    SURGEON("외과 의사"),                // 외과 의사
    PEDIATRICIAN("소아과 의사"),           // 소아과 의사
    GYNECOLOGIST("산부인과 의사"),           // 산부인과 의사
    DERMATOLOGIST("피부과 의사"),          // 피부과 의사
    PSYCHIATRIST("정신과 의사"),           // 정신과 의사
    ORTHOPEDIC_SURGEON("정형외과 의사"),     // 정형외과 의사
    RADIOLOGIST("방사선과 의사"),            // 방사선과 의사
    CARDIOLOGIST("심장내과 의사"),           // 심장내과 의사
    NEUROLOGIST("신경과 의사"),            // 신경과 의사
    OPHTHALMOLOGIST("안과 의사"),        // 안과 의사
    ENT_SPECIALIST("이비인후과 의사"),         // 이비인후과 의사
    DENTIST("치과 의사"),                // 치과 의사
    VETERINARIAN("수의사"),           // 수의사
    PHARMACIST("약사"),             // 약사
    UNLICENSED("무면허");              // 무면허

    private final String doctorTp;
}
