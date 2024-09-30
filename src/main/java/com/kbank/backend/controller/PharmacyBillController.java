package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.response.PharmacyBillResponse;
import com.kbank.backend.service.PharmacyBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/patient/prescription")
@RequiredArgsConstructor
public class PharmacyBillController {
    private final PharmacyBillService pharmacyBillService;

    //전체 리스트 조회
    @GetMapping("/pharmacyBill")
    public ResponseDto<List<PharmacyBillResponse>> listPharmacyBill(){
        return ResponseDto.ok(pharmacyBillService.getAllBills());
    }


    //특정 처방전 id로 조회
    @GetMapping("/pharmacyBill/{id}")
    public ResponseDto<PharmacyBillResponse> detailHospitalBill(@PathVariable("id") long id){
        return ResponseDto.ok(pharmacyBillService.getBillByPrescriptionFk(id));
    }
}
