package com.kbank.backend.controller;


import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.response.ReportResponseDto;
import com.kbank.backend.service.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/patient/report")
public class ReportController {

    private final ReportService reportService;

    // 수정 요함
    // 처방전 주키를 가지고 리포트 가져오기
//    @GetMapping("/get/{id}")
//    public ResponseDto<ReportResponseDto> getReport(@RequestParam(name = "userId") Long userId,
//                                                    @PathVariable("id") Long id) {
//        return ResponseDto.ok(reportService.getReportByPrescription(id));
//    }
}
