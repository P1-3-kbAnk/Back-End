package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.ChemistRequestDto;
import com.kbank.backend.service.ChemistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pharmacy")
@RequiredArgsConstructor
public class PharmacyController {

    private final ChemistService chemistService;

    @PostMapping("/register")
    public ResponseDto<Boolean> createDoctor(@RequestBody @Valid ChemistRequestDto chemistRequestDto) {
        return ResponseDto.created(chemistService.createChemist(chemistRequestDto));
    }
}
