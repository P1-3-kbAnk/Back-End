package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.response.MedicineResponseDto;
import com.kbank.backend.service.MedicineIntakeService;
import com.kbank.backend.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medi")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;
    private final MedicineIntakeService medicineIntakeService;

    // 복용중인 약 상세 정보 조회
    @GetMapping("/detail/{medicineId}")
    public ResponseDto<MedicineResponseDto> medicineDetail(@RequestParam(name = "userId") Long userId,
                                                           @PathVariable("medicineId") Long medicineFk) {
        return ResponseDto.ok(medicineService.medicineDetail(userId, medicineFk));
    }

    @GetMapping("/list")
    public ResponseDto<?> medicineList() {
        return ResponseDto.ok(medicineService.getMedicineList());
    }

    // 수정 요함
    // 현재 복용 중인 약물 조회
//    @GetMapping("/taking/list")
//    public ResponseDto<?> takingMedcineList(@RequestParam(name = "userId") Long userId) {
//
//        return ResponseDto.ok(medicineIntakeService.getMedicineIntakeByUser(userId));
//    }
}
