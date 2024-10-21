package com.kbank.backend.controller.prescription;

import com.kbank.backend.annotation.UserId;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.service.prescription.ChemistPrescriptionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chemists/prescription")
@RequiredArgsConstructor
public class ChemistPrescriptionController {

    private final ChemistPrescriptionService chemistPrescriptionService;

    @GetMapping("/{id}")
    public ResponseDto<?> prescriptionDetail(@PathVariable("id") Long prescriptionId) {
        return ResponseDto.ok(chemistPrescriptionService.prescriptionDetail(prescriptionId));
    }

    @GetMapping("/list")
    public ResponseDto<?> receivedPrescription(@UserId Long userId,
                                               @RequestParam(name = "pageIndex") @Valid @NotNull @Min(0) Integer pageIndex,
                                               @RequestParam(name = "pageSize") @Valid @NotNull @Min(1) Integer pageSize) {
        return ResponseDto.ok(chemistPrescriptionService.getAllPrescriptionList(userId, pageIndex, pageSize));
    }

    @PatchMapping("/{id}")
    public ResponseDto<Boolean> updatePrescriptionSt(@UserId Long chemistId,
                                                     @PathVariable("id") Long prescriptionId) {
        return ResponseDto.ok(chemistPrescriptionService.updatePrescriptionSt(chemistId, prescriptionId));
    }

}
