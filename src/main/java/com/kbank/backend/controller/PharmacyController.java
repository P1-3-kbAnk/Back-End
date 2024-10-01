package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.ChemistRequestDto;
import com.kbank.backend.dto.response.PrescriptionHtmlResponseDto;
import com.kbank.backend.service.ChemistService;
import com.kbank.backend.service.PharmacyService;
import com.kbank.backend.service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacy")
@RequiredArgsConstructor
public class PharmacyController {

    private final ChemistService chemistService;
    private final PharmacyService pharmacyService;

    @PostMapping("/register")
    public ResponseDto<Boolean> createDoctor(@RequestBody @Valid ChemistRequestDto chemistRequestDto) {
        return ResponseDto.created(chemistService.createChemist(chemistRequestDto));
    }

    // 처방전 상태여부 업데이트
    @PatchMapping("/prescription/{id}")
    public ResponseDto<String> updatePrescriptionSt(@PathVariable("id") long id) {
        pharmacyService.updatePrescriptionSt(id);
        return ResponseDto.ok("success");
    }
    @GetMapping("/list/{id}")
    public ResponseDto<List<PrescriptionHtmlResponseDto>> receivedPrescription(@PathVariable("id") long id){
        List<PrescriptionHtmlResponseDto> prescriptionList = pharmacyService.receivedPrescription(id);
        return ResponseDto.ok(prescriptionList);
    }



}
