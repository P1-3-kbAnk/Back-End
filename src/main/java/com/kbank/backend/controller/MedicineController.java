package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.response.MedicineResponseDto;
import com.kbank.backend.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medi")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping("/detail/{medicineId}")
    public ResponseDto<MedicineResponseDto> medicineDetail(@RequestParam Long userId,
                                                           @PathVariable("medicineId") Long medicineFk) {
        return ResponseDto.ok(medicineService.medicineDetail(userId, medicineFk));
    }
}
