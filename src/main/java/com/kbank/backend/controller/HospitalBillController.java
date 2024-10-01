package com.kbank.backend.controller;

import com.kbank.backend.domain.HospitalBill;
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
    public ResponseEntity<List<HospitalBillResponse>> listHospitalBill(){
        List<HospitalBillResponse> hospitalResponseList=hospitalBillService.getAllBills()
                .stream()
                .map(HospitalBillResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(hospitalResponseList);
    }

    //특정 id로 조회
    @GetMapping("/hospitalBill/{id}")
    public ResponseEntity<HospitalBillResponse> detailHospitalBill(@PathVariable("id") long id){

        HospitalBillResponse hospitalBillResponse = hospitalBillService.getBillById(id);

        return ResponseEntity.ok(hospitalBillResponse);
    }





}
