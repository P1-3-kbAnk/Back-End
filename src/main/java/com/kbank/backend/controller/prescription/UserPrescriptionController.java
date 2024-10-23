package com.kbank.backend.controller.prescription;

import com.kbank.backend.annotation.UserId;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.service.PayService;
import com.kbank.backend.service.prescription.UserPrescriptionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/prescriptions")
@RequiredArgsConstructor
public class UserPrescriptionController {

    private final UserPrescriptionService userPrescriptionService;
    private final PayService payService;

    @GetMapping("")
    public ResponseDto<?> getUserPrescription(@UserId Long userId,
                                              @RequestParam(name = "pageIndex") @Valid @NotNull @Min(0) Integer pageIndex,
                                              @RequestParam(name = "pageSize") @Valid @NotNull @Min(1) Integer pageSize) {
        return ResponseDto.ok(userPrescriptionService.getAllPrescriptionList(userId, pageIndex, pageSize));
    }

    @GetMapping("/new")
    public ResponseDto<?> getUserNotReceivedPrescription(@UserId Long userId) {
        return ResponseDto.ok(userPrescriptionService.notReceivedPrescriptionList(userId));
    }

    @GetMapping("/{id}")
    public ResponseDto<?> getPrescriptionDetail(@PathVariable("id") Long prescriptionId) {
        return ResponseDto.ok(userPrescriptionService.prescriptionDetail(prescriptionId));
    }

    @PatchMapping("/{id}/pay")
    public ResponseDto<Boolean> payment(
            @RequestParam(name = "deductedAmount") Long deductedAmount,  // 추가된 요청 파라미터
            @PathVariable(name = "id") Long prescriptionId) {
        return ResponseDto.ok(payService.payment(prescriptionId,deductedAmount));
    }

    @PatchMapping("/{id}/insurance")
    public ResponseDto<Boolean> updatePrescriptionSt(@PathVariable("id") Long prescriptionId) {
        return ResponseDto.ok(userPrescriptionService.updateInsuranceSt(prescriptionId));
    }

}
