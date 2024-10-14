package com.kbank.backend.controller;

import com.kbank.backend.annotation.UserId;
import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.PrescriptionRequestDto;
import com.kbank.backend.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient/prescription")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    // 처방전 저장
    @PostMapping("/post")
    public ResponseDto<Long> savePrescription(@UserId Long doctorId,
                                              @RequestBody PrescriptionRequestDto prescriptionRequestDto) {
        return ResponseDto.created(prescriptionService.createPrescription(doctorId, prescriptionRequestDto));
    }

    // 수정 요함
    // 처방 받아야 할 처방전 조회
    @GetMapping("/new/list")
    public ResponseDto<?> notReceivedPrescriptionList(@UserId Long userId) {
        return ResponseDto.ok(prescriptionService.notReceivedPrescriptionList(userId));
    }
//
//    //전체 조회
//    @GetMapping("/list")
//    public ResponseDto<List<PrescriptionHtmlResponseDto>> getAllPrescriptionHtmls(@RequestParam(name = "userId") Long userId) {
//        List<PrescriptionHtmlResponseDto> prescriptionList = prescriptionService.getAllPrescriptionHtmls();
//        return ResponseDto.ok(prescriptionList);
//    }
//
//    //id별 처방전 조회
//    @GetMapping("/detail/{id}")
//    public ResponseDto<PrescriptionHtmlResponseDto> getPrescription(@RequestParam(name = "userId") Long userId,
//                                                                    @PathVariable("id") Long id){
//        return ResponseDto.ok(prescriptionService.getPrescriptionHtml(id));
//    }

}
