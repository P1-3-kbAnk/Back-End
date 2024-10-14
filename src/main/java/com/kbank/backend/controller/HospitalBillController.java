package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.response.HospitalBillResponseDto;
import com.kbank.backend.service.HospitalBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient/prescription")
@RequiredArgsConstructor
public class HospitalBillController {

    private final HospitalBillService hospitalBillService;



    //특정 처방전 id로 조회
    @GetMapping("/hospitalBill/{prescriptionId}")
    public ResponseDto<HospitalBillResponseDto> detailHospitalBill(@PathVariable("prescriptionId") Long PrescriptionId){
        return ResponseDto.ok(hospitalBillService.getBillByPrescriptionFk(PrescriptionId));
    }

}
