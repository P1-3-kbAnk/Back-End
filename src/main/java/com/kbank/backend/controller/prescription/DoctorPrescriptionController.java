package com.kbank.backend.controller.prescription;

import com.kbank.backend.annotation.UserId;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.PrescriptionRequestDto;
import com.kbank.backend.service.PayService;
import com.kbank.backend.service.prescription.DoctorPrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors/prescription")
@RequiredArgsConstructor
public class DoctorPrescriptionController {

    private final PayService payService;
    private final DoctorPrescriptionService doctorPrescriptionService;

    @PostMapping("")
    public ResponseDto<Long> createPrescription(@UserId Long doctorId,
                                              @RequestBody PrescriptionRequestDto prescriptionRequestDto) {
        return ResponseDto.created(doctorPrescriptionService.createPrescription(doctorId, prescriptionRequestDto));
    }

    //의사 결제 요청 결제
    @PatchMapping("/{id}/pay")
    public ResponseDto<Boolean> doctorPayment(@PathVariable(name = "id") Long prescriptionId) {
        return ResponseDto.ok(payService.doctorPayment(prescriptionId));
    }

}
