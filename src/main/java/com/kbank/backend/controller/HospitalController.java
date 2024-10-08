package com.kbank.backend.controller;

import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.DoctorRequestDto;
import com.kbank.backend.service.DiseaseService;
import com.kbank.backend.service.DoctorService;
import com.kbank.backend.dto.response.DoctorResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final DoctorService doctorService;
    private final DiseaseService diseaseService;

    @PostMapping("/register")
    public ResponseDto<Boolean> createDoctor(@RequestBody @Valid DoctorRequestDto doctorRequestDto) {
        return ResponseDto.created(doctorService.createDoctor(doctorRequestDto));
    }

    @GetMapping("/disease")
    public ResponseDto<?> getDiseaseList() {
        return ResponseDto.ok(diseaseService.getDiseaseList());
    }

    @GetMapping("doctor/get")
    public ResponseDto<DoctorResponseDto> getDoctor(@RequestParam(name = "doctorPk") Long doctorPk) {
        return ResponseDto.ok(doctorService.doctorInfo(doctorPk));
    }
}
