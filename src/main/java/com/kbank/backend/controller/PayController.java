package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.response.PayResponseDto;
import com.kbank.backend.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;

    //자동 결제

    @PostMapping("/{prescriptionId}")
    public ResponseEntity<PayResponseDto> payment(
            @PathVariable(name = "prescriptionId") Long prescriptionId) {
        PayResponseDto response = payService.payment(prescriptionId);
        return ResponseEntity.ok(response);
    }

}
