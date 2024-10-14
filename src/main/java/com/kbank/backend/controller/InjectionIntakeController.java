package com.kbank.backend.controller;


import com.kbank.backend.annotation.Date;
import com.kbank.backend.annotation.UserId;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.service.InjectionIntakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/inj")
@RequiredArgsConstructor
public class InjectionIntakeController {

    private final InjectionIntakeService injectionIntakeService;

    @GetMapping("/taking/list")
    public ResponseDto<?> getInjectionIntakesByDate(@UserId Long userId, @RequestParam("date") @Date String date) {
        return ResponseDto.ok(injectionIntakeService.getInjectionIntakeByDate(userId, LocalDate.parse(date)));
    }

    @PatchMapping("/taking/comp/{id}")
    public ResponseDto<Boolean> updateInjectionIntake(@PathVariable("id") Long id) {
        return ResponseDto.ok(injectionIntakeService.updateEatSt(id));
    }

}
