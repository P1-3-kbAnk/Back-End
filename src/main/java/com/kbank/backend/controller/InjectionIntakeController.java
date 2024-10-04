package com.kbank.backend.controller;


import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.repository.InjectionIntakeRepository;
import com.kbank.backend.service.InjectionIntakeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inj")
@RequiredArgsConstructor
public class InjectionIntakeController {

    private final InjectionIntakeService injectionIntakeService;

    @GetMapping("/taking/list")
    public ResponseDto<List<Map<?, ?>>> getInjectionIntakesByDate(@RequestParam("userId") Long userId, @RequestParam("date") String date) {
        return ResponseDto.ok(injectionIntakeService.getInjectionIntakeByDate(userId, date));
    }

    @PatchMapping("/taking/comp/{id}")
    public ResponseDto<String> updateInjectionIntake(@RequestParam("userId") Long userId, @PathVariable("id") Long id) {
        return ResponseDto.ok(injectionIntakeService.updateEatSt(userId, id) ? "success" : "fail");
    }

}
