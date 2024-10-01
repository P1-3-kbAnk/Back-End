package com.kbank.backend.controller;

import com.kbank.backend.domain.HospitalBill;
import com.kbank.backend.domain.PharmacyBill;
import com.kbank.backend.dto.response.PharmacyBillResponse;
import com.kbank.backend.service.PharmacyBillService;
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
public class PharmacyBillController {
    private final PharmacyBillService pharmacyBillService;

    //전체 리스트 조회
    @GetMapping("/pharmacyBill")
    public ResponseEntity<List<PharmacyBillResponse>> listPharmacyBill(){
        List<PharmacyBillResponse> pharmacyBillResponses=pharmacyBillService.getAllBills()
                .stream()
                .map(PharmacyBillResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pharmacyBillResponses);
    }

    //특정 id로 조회
    @GetMapping("/pharmacyBill/{id}")
    public ResponseEntity<PharmacyBillResponse> detailPharmacyBill(@PathVariable("id") long id){

        PharmacyBillResponse pharmacyBillResponse = pharmacyBillService.getBillById(id);

        return ResponseEntity.ok(pharmacyBillResponse);
    }
}
