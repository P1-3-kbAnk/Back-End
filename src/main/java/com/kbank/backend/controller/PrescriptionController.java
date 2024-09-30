package com.kbank.backend.controller;


import com.kbank.backend.domain.Prescription;
import com.kbank.backend.dto.request.PrescriptionRequestDto;
import com.kbank.backend.dto.response.PrescriptionResponseDto;
import com.kbank.backend.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/patient/prescription")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;



    // 특정 처방전과 관련된 질병 조회
    @GetMapping("/diseases/{id}")
    public ResponseEntity<PrescriptionResponseDto> getPrescriptionWithDisease(@PathVariable("id") Long id) {
        PrescriptionResponseDto responseDto = prescriptionService.getPrescriptionWithDisease(id);
        return ResponseEntity.ok(responseDto);
    }

    // 처방전과 관련 질병 정보를 함께 입력하는 API
    @PostMapping("/create")
    public ResponseEntity<PrescriptionResponseDto> createPrescriptionWithDiseases(@RequestBody PrescriptionRequestDto requestDto) {
        PrescriptionResponseDto responseDto = prescriptionService.createPrescriptionWithDiseases(requestDto);
        return ResponseEntity.ok(responseDto);
    }
//    //전체 리스트 조회
//    @GetMapping("/list")
//    public ResponseEntity<List<PrescriptionResponseDto>> listPrescription(){
//        List<PrescriptionResponseDto> prescriptionResponseDtoList =prescriptionService.findAll()
//                .stream()
//                .map(PrescriptionResponseDto::toEntity)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(prescriptionResponseDtoList);
//    }


//    //id로 하나 조회
//    @GetMapping("/detail/{id}")
//    public ResponseEntity<PrescriptionResponseDto> detialPrescription(@PathVariable("id") long id){
//        Optional<Prescription> prescription= prescriptionService.findById(id);
//
//        PrescriptionResponseDto prescriptionResponseDto = PrescriptionResponseDto.toEntity(prescription.get());
//
//        return ResponseEntity.ok(prescriptionResponseDto);
//    }
//    //처방받지 않는 처방전들 리스트
//    @GetMapping("/new/list")
//    public ResponseEntity<List<PrescriptionResponseDto>> notRecivedPrescription(){
//        List<PrescriptionResponseDto> prescriptionResponseDtoList =prescriptionService.findNotReceived()
//                .stream()
//                .map(PrescriptionResponseDto::toEntity)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(prescriptionResponseDtoList);
//    }
    @PostMapping("/post")
    public ResponseEntity<Boolean> createPrescription(@RequestBody PrescriptionRequestDto request) {
        boolean isSaved = prescriptionService.createPrescription(request);
        return ResponseEntity.ok(isSaved);
    }


}
