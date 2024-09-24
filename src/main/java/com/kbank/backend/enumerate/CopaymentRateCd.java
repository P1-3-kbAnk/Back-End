package com.kbank.backend.enumerate;

/*
제목 : 본인부담률 enum
설명 : 주석 참고
담당자 : 김성헌
*/

public enum CopaymentRateCd {
    FULL_COVERAGE,      // 전액 본인 부담 (100%)
    PARTIAL_COVERAGE,   // 부분 부담 (일반적인 본인부담률)
    LOW_INCOME,         // 저소득층
    DISABLED,           // 장애인
    ELDERLY,            // 노인 (65세 이상)
    PREGNANT,           // 임산부
    MILITARY,           // 군인
    EXEMPTION           // 면제 (본인 부담 없음)
}
