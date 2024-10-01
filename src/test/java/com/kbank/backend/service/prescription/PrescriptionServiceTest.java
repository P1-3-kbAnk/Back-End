package com.kbank.backend.service.prescription;

import com.kbank.backend.config.AppConfig;

import com.kbank.backend.dto.request.PrescriptionRequestDto;
import com.kbank.backend.dto.response.PrescriptionResponseDto;
import com.kbank.backend.service.PrescriptionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@Slf4j
class PrescriptionServiceTest {

    @Autowired
    private PrescriptionService service;
    // PrescriptionRequest 객체 생성
    @Test
    void createPrescription() {
        PrescriptionRequestDto request = PrescriptionRequestDto.builder()
                .doctorId(1L)  // Doctor ID
                .userId(1L)    // User ID
                .chemistId(1L) // Chemist ID
                .createYmd(LocalDateTime.now())  // 생성 날짜
                .prescriptionNo(1234)          // 처방전 번호
                .duration(123)                   // 기간
                .description("fff")              // 설명
                .prescriptionSt(false)           // 처방 상태
                .insuranceSt(false)              // 보험 상태
                .build();

        // 서비스 메서드 호출

        PrescriptionResponseDto response = service.createPrescription(request);
        System.out.println("test"+response);

    }
}