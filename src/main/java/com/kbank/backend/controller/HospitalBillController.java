package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.response.HospitalBillResponseDto;
import com.kbank.backend.service.HospitalBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patient/prescription")
@RequiredArgsConstructor
public class HospitalBillController {

    private final HospitalBillService hospitalBillService;



    //특정 처방전 id로 조회
    @GetMapping("/hospitalBill/{id}")
    public ResponseDto<HospitalBillResponseDto> detailHospitalBill(@PathVariable("id") long id){
        return ResponseDto.ok(hospitalBillService.getBillByPrescriptionFk(id));
    }




}
