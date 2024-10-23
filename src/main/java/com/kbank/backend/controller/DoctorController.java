package com.kbank.backend.controller;

import com.kbank.backend.annotation.UserId;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.service.DiseaseService;
import com.kbank.backend.service.DoctorService;
import com.kbank.backend.dto.response.DoctorResponseDto;
import com.kbank.backend.service.InjectionService;
import com.kbank.backend.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DiseaseService diseaseService;
    private final MedicineService medicineService;
    private final InjectionService injectionService;

    @GetMapping("")
    public ResponseDto<DoctorResponseDto> getDoctor(@UserId Long doctorPk) {
        return ResponseDto.ok(doctorService.doctorInfo(doctorPk));
    }

    @GetMapping("/diseases")
    public ResponseDto<?> getDiseaseList() {
        return ResponseDto.ok(diseaseService.getDiseaseList());
    }

    @GetMapping("/medicines")
    public ResponseDto<?> medicineList() {
        return ResponseDto.ok(medicineService.getMedicineList());
    }

    @GetMapping("/injections")
    public ResponseDto<?> getInjectionList() {
        return ResponseDto.ok(injectionService.getInjectionList());
    }
}