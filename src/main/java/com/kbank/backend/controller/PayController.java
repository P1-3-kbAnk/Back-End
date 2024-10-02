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

    @PutMapping("/{prescriptionId}")
    public ResponseDto<PayResponseDto> payment(
            @RequestParam(name = "userId") Long userId,
            @PathVariable(name = "prescriptionId") Long prescriptionId) {

        return ResponseDto.ok(payService.payment(userId, prescriptionId));

    }

}
