package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.service.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/patient/report")
public class ReportController {

    private final ReportService reportService;

     //처방전 주키를 가지고 리포트 가져오기
    @GetMapping("/get/{id}")
    public ResponseDto<Map<?, ?>> getReport(@RequestParam(name = "userId") Long userId,
                                           @PathVariable("id") Long PrescriptionId) {
        return ResponseDto.ok(reportService.getReportByPrescription(userId, PrescriptionId));
    }
}
