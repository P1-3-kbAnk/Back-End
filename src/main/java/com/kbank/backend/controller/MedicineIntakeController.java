package com.kbank.backend.controller;


import com.kbank.backend.annotation.Date;
import com.kbank.backend.annotation.UserId;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.service.MedicineIntakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/medi")
@RequiredArgsConstructor
public class MedicineIntakeController {

    private final MedicineIntakeService medicineIntakeService;


    // 날짜별로 복약해야하는 약 리스트 가져옴
    @GetMapping("/taking/list")
    public ResponseDto<?> dateMedicineIntake(@UserId Long userId, @RequestParam("date") @Date String date) {
        return ResponseDto.ok(medicineIntakeService.getMedicineIntakeByDate(userId, LocalDate.parse(date)));
    }

    @PatchMapping("/taking/comp/{id}")
    public ResponseDto<Boolean> updateEatSt(@PathVariable("id") Long medInkPk) {
        return ResponseDto.ok(medicineIntakeService.updateEatSt(medInkPk));
    }


}
