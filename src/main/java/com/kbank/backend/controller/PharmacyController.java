package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.ChemistRequestDto;
import com.kbank.backend.service.ChemistService;
import com.kbank.backend.service.PharmacyService;
import com.kbank.backend.service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pharmacy")
@RequiredArgsConstructor
public class PharmacyController {

    private final ChemistService chemistService;
    private final PrescriptionService prescriptionService;
    private final PharmacyService pharmacyService;

    @PostMapping("/register")
    public ResponseDto<Boolean> createChemist(@RequestBody @Valid ChemistRequestDto chemistRequestDto) {
        return ResponseDto.created(chemistService.createChemist(chemistRequestDto));
    }

    @PatchMapping("/prescription/{id}")
    public ResponseDto<Boolean> updatePrescriptionSt(@RequestParam(name = "chemistId") Long chemistId,
                                                     @PathVariable("id") Long prescriptionId) {
        return ResponseDto.ok(prescriptionService.updatePrescriptionSt(prescriptionId));
    }

    // 처방전 상세조회
    @GetMapping("/prescription/detail/{id}")
    public ResponseDto<?> prescriptionDetail(@RequestParam(name = "chemistId") Long chemistId,
                                                     @PathVariable("id") Long prescriptionId) {
        return ResponseDto.ok(prescriptionService.prescriptionDetail(prescriptionId));
    }

    // 수정 요함
    // 처방전 상태여부 업데이트
//    @PatchMapping("/prescription/{id}")
//    public ResponseDto<String> updatePrescriptionSt(@RequestParam(name = "userId") Long userId,
//                                                    @PathVariable("id") Long id) {
//        pharmacyService.updatePrescriptionSt(id);
//        return ResponseDto.ok("success");
//    }
//    @GetMapping("/list/{id}")
//    public ResponseDto<List<PrescriptionHtmlResponseDto>> receivedPrescription(@RequestParam(name = "userId") Long userId,
//                                                                               @PathVariable("id") Long id){
//        List<PrescriptionHtmlResponseDto> prescriptionList = pharmacyService.receivedPrescription(id);
//        return ResponseDto.ok(prescriptionList);
//    }

}
