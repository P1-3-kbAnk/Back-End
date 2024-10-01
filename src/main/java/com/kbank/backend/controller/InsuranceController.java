package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/insurance")
@RequiredArgsConstructor
public class InsuranceController {
    private final PrescriptionService prescriptionService;

    @PatchMapping("/update/{id}")
    public ResponseDto<String> updatePrescriptionSt(@PathVariable("id") long id) {
        prescriptionService.updateInsuranceSt(id);
        return ResponseDto.ok("success");
    }

}
