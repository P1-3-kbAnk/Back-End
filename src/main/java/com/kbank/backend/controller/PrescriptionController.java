package com.kbank.backend.controller;


import com.kbank.backend.domain.Prescription;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.PrescriptionRequest;
import com.kbank.backend.dto.request.QrRequest;
import com.kbank.backend.dto.response.PrescriptionHtmlResponseDto;
import com.kbank.backend.dto.response.PrescriptionResponse;
import com.kbank.backend.dto.response.QrResponse;
import com.kbank.backend.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patient/prescription")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;


    //전체 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<List<PrescriptionResponse>> listPrescription(){
        List<PrescriptionResponse> prescriptionResponseList=prescriptionService.findAll()
                .stream()
                .map(PrescriptionResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(prescriptionResponseList);
    }
    //id로 하나 조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<PrescriptionResponse> detialPrescription(@PathVariable("id") long id){
        Optional<Prescription> prescription= prescriptionService.findById(id);

        PrescriptionResponse prescriptionResponse = new PrescriptionResponse(prescription.get());

        return ResponseEntity.ok(prescriptionResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<PrescriptionHtmlResponseDto> getPrescription(@PathVariable("id") long id){
        return ResponseDto.ok(prescriptionService.getPrescriptionHtml(id));
    }



    //처방받지 않는 처방전들 리스트
//    @GetMapping("/new/list")
//    public ResponseEntity<List<PrescriptionResponse>> notRecivedPrescription(){
//        List<PrescriptionResponse> prescriptionResponseList=prescriptionService.findNotRecived()
//                .stream()
//                .map(PrescriptionResponse::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(prescriptionResponseList);
//    }
//    @PostMapping("/post")
//    public ResponseEntity<PrescriptionResponse> createPrescription(@RequestBody PrescriptionRequest request){
//        PrescriptionResponse prescriptionResponse=prescriptionService.createPrescription(request);
//        return ResponseEntity.ok(prescriptionResponse);
//    }

//    @PostMapping("/qr")
//    public ResponseDto<QrResponse> createQr(@RequestBody QrRequest request){
//        return ResponseDto.ok(prescriptionService.getPreQRByPreNo(request.getPrescriptionNo()));
//    }




}
