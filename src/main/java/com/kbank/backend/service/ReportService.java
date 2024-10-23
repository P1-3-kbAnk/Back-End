package com.kbank.backend.service;

import com.kbank.backend.controller.ReportRestTemplate;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.Report;
import com.kbank.backend.dto.request.ReportRequestDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportRestTemplate restTemplate;
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;
    private final PrescriptionDiseaseRepository prescriptionDiseaseRepository;

    public List<String> getMedicineList(Prescription prescription){
        List<String> res = new ArrayList<>();
        prescriptionMedicineRepository
                .findMedicineByPrescription(prescription)
                .forEach(e -> res.add(e.getMedicineNm()));

        return res;
    }

     // 없을떈 생성하는 메소드를 호출해야하므로 트랜잭션 처리 필수
    public Map<String, String> getReportByPrescription(Long prescriptionId) {

        Prescription prescription = prescriptionRepository
                .findById(prescriptionId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        Report report = reportRepository // 리포트 가져옴
                .findByReportPrescription(prescription)
                .orElseGet(() -> {
                    List<String> res = new ArrayList<>(); // 질병 코드 리스트
                    Map<String, String> query = new HashMap<>(); // GPT에게 보낼 쿼리
                    ReportRequestDto responseFromFa;
                    prescriptionDiseaseRepository.findByPreDisPrescription(prescription)
                                .forEach(e -> res.add(e.getPreDisDisease().getDiseaseCd()));

                    query.put("medi", getMedicineList(prescription).toString()); // 약 리스트
                    query.put("code", res.toString()); // 질병 코드 리스트

                    try{
                        responseFromFa = restTemplate.getReport(query);// fast api 에서 온 응답
                    } catch (CommonException e) {
                        throw new CommonException(ErrorCode.ERR_FAST_API);
                    }

                    Report reportResponse = Report
                            .builder()
                            .intakeMethod(String.join(",", responseFromFa.getCaution()))
                            .food(String.join(",", responseFromFa.getFood()))
                            .exercise(String.join(",", responseFromFa.getExercise()))
                            .reportPrescription(prescription)
                            .build();
                        // 리스트로 온 응답을 하나의 문자열로 변환하여 저장

                    log.info(reportResponse.toString());
                    reportRepository.save(reportResponse);

                    return reportResponse;
                });

        // response
        Map<String, String> response = new HashMap<>();

        response.put("reportPk", String.valueOf(report.getReportPk()));
        response.put("prescriptionPk", String.valueOf(report.getReportPrescription().getPrescriptionPk()));
        response.put("intakeMethod", report.getIntakeMethod());
        response.put("food", report.getFood());
        response.put("exercise", report.getExercise());

        return response;
    }
}
