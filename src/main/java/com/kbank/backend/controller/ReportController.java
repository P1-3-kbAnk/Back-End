package com.kbank.backend.controller;

import com.kbank.backend.annotation.UserId;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.service.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/patient/report")
public class ReportController {

    private final ReportService reportService;

    //처방전 주키를 가지고 리포트 가져오기
    @GetMapping("/get/{id}")
    public ResponseDto<?> getReport(@PathVariable("id") Long PrescriptionId) {
        return ResponseDto.ok(reportService.getReportByPrescription(PrescriptionId));
    }
}