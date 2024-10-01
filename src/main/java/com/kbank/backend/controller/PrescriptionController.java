package com.kbank.backend.controller;


import com.kbank.backend.dto.ResponseDto;
import com.kbank.backend.dto.request.PrescriptionHtmlRequestDto;
import com.kbank.backend.dto.response.PrescriptionHtmlResponseDto;
import com.kbank.backend.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/patient/prescription")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;



    //처방 받아야 할 조회
    @GetMapping("/new/list")
    public ResponseDto<List<PrescriptionHtmlResponseDto>> notReceivedPrescriptionHtmls(@RequestParam(name = "userId") Long userId) {
        List<PrescriptionHtmlResponseDto> prescriptionList = prescriptionService.notReceivedPrescriptionHtmls();
        return ResponseDto.ok(prescriptionList);
    }

    //전체 조회
    @GetMapping("/list")
    public ResponseDto<List<PrescriptionHtmlResponseDto>> getAllPrescriptionHtmls(@RequestParam(name = "userId") Long userId) {
        List<PrescriptionHtmlResponseDto> prescriptionList = prescriptionService.getAllPrescriptionHtmls();
        return ResponseDto.ok(prescriptionList);
    }

    //id별 처방전 조회
    @GetMapping("/detail/{id}")
    public ResponseDto<PrescriptionHtmlResponseDto> getPrescription(@RequestParam(name = "userId") Long userId,
                                                                    @PathVariable("id") Long id){
        return ResponseDto.ok(prescriptionService.getPrescriptionHtml(id));
    }

    // 여기 반환 값 어떻게 해야 함요?
    @PostMapping("/post")
    public ResponseDto<Object> savePrescription(@RequestParam(name = "userId") Long userId,
                                                @RequestBody PrescriptionHtmlRequestDto preHtmlReqDto) {

        prescriptionService.createPrescription(preHtmlReqDto);
        return ResponseDto.ok("처방전이 성공적으로 저장되었습니다."); // 성공 응답

    }





}
