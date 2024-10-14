package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PayController {

    private final PayService payService;

    //자동 결제
    @PatchMapping("/{prescriptionId}")
    public ResponseDto<Boolean> payment(
            @RequestParam(name = "deductedAmount") Long deductedAmount,  // 추가된 요청 파라미터
            @PathVariable(name = "prescriptionId") Long prescriptionId) {
        return ResponseDto.ok(payService.payment(prescriptionId,deductedAmount));
    }

    //의사 결제 요청 결제
    @PatchMapping("/doctor/{prescriptionId}")
    public ResponseDto<Boolean> doctoerPayment(// 추가된 요청 파라미터
            @PathVariable(name = "prescriptionId") Long prescriptionId) {
        return ResponseDto.ok(payService.doctorPayment(prescriptionId));
    }


}
