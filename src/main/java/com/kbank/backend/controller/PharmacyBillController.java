package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.response.PharmacyBillResponseDto;
import com.kbank.backend.service.PharmacyBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/patient/prescription")
@RequiredArgsConstructor
public class PharmacyBillController {
    private final PharmacyBillService pharmacyBillService;


    //특정 처방전 id로 조회
    @GetMapping("/pharmacyBill/{id}")
    public ResponseDto<PharmacyBillResponseDto> detailHospitalBill(@RequestParam(name = "userId") Long userId, @PathVariable("id") long id){
        return ResponseDto.ok(pharmacyBillService.getBillByPrescription(userId,id));
    }
}
