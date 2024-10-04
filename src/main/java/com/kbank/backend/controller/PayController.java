package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;

    //자동 결제
    @PutMapping("/{prescriptionId}")
    public ResponseDto<Map<?, ?>> payment(
            @RequestParam(name = "userId") Long userId,
            @PathVariable(name = "prescriptionId") Long prescriptionId) {

        Map<?, ?> paymentResult = payService.payment(userId, prescriptionId);
        return ResponseDto.ok(paymentResult);
    }

}
