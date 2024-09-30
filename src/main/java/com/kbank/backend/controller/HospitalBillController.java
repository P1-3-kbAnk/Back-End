package com.kbank.backend.controller;

import com.kbank.backend.domain.HospitalBill;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.response.HospitalBillResponse;
import com.kbank.backend.service.HospitalBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patient/prescription")
@RequiredArgsConstructor
public class HospitalBillController {

    private final HospitalBillService hospitalBillService;

    //전체 리스트 조회
    @GetMapping("/hospitalBill")
    public ResponseDto<List<HospitalBillResponse>> listHospitalBill(){
        return ResponseDto.ok(hospitalBillService.getAllBills());
    }


    //특정 처방전 id로 조회
    @GetMapping("/hospitalBill/{id}")
    public ResponseDto<HospitalBillResponse> detailHospitalBill(@PathVariable("id") long id){
        return ResponseDto.ok(hospitalBillService.getBillByPrescriptionFk(id));
    }




}
