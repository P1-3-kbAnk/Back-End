package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/insurance")
@RequiredArgsConstructor
public class InsuranceController {
    private final PrescriptionService prescriptionService;

    @PatchMapping("/update/{id}")
    public ResponseDto<Boolean> updatePrescriptionSt(@RequestParam(name = "userId") Long userId,
                                                    @PathVariable("id") Long prescriptionId) {
        return ResponseDto.ok(prescriptionService.updateInsuranceSt(prescriptionId));
    }

}
