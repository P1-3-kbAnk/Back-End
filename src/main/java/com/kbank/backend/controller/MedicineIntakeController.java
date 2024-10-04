package com.kbank.backend.controller;


import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.response.MedicineIntakeResponseDto;
import com.kbank.backend.service.MedicineIntakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medi")
@RequiredArgsConstructor
public class MedicineIntakeController {

    private final MedicineIntakeService medicineIntakeService;


    // 날짜별로 복약해야하는 약 리스트 가져옴
    @GetMapping("/taking/list")
    public ResponseDto<List<Map<?, ?>>> dateMedicineIntake(@RequestParam("userId") Long userId, @RequestParam("date") String date){
        return ResponseDto.ok(medicineIntakeService.getMedicineIntakeByDate(userId, date));
    }

    @PatchMapping("/taking/comp/{id}")
    public ResponseDto<String> updateEatSt(@RequestParam("userId") Long userId, @PathVariable("id") Long medInkPk) {
        return ResponseDto.ok(medicineIntakeService.updateEatSt(userId, medInkPk) ? "success" : "fail");
    }


}
