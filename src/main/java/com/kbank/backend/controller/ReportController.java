package com.kbank.backend.controller;


import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.response.ReportResponse;
import com.kbank.backend.service.report.ReportServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/patient/report")
public class ReportController {

    private final ReportServiceImpl reportService;

    @GetMapping("/get/{id}")
    public ResponseDto<ReportResponse> getReport(@PathVariable("id") Long id) {
        return ResponseDto.ok(reportService.getReportByPrescription(id));
    }




}
