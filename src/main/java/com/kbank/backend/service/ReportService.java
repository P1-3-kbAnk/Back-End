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

    // report 생성
    public Report create(Long prescriptionId) {
        try {

            // 처방전 받아옴
            Prescription prescription = prescriptionRepository
                    .findById(prescriptionId)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

            List<String> res = new ArrayList<>(); // 질병 코드 리스트
            Map<String, String> query = new HashMap<>(); // GPT에게 보낼 쿼리

            prescriptionDiseaseRepository.findByPreDisPrescription(prescription)
                    .forEach(e -> res.add(e.getPreDisDisease().getDiseaseCd()));

            query.put("medi", getMedicineList(prescription).toString()); // 약 리스트
            query.put("code", res.toString()); // 질병 코드 리스트

            // fast api 에서 온 응답
            ReportRequestDto responseFromFa = restTemplate.getReport(query);

            Report report = Report
                    .builder()
                    .reportPrescription(prescription)
                    .intakeMethod(String.join(",", responseFromFa.getCaution()))
                    .food(String.join(",", responseFromFa.getFood()))
                    .exercise(String.join(",", responseFromFa.getExercise()))
                    .build();


            // 리스트로 온 응답을 하나의 문자열로 변환하여 저장
            reportRepository.save(report);

            return report;

        } catch (CommonException e) {
            throw new CommonException(ErrorCode.ERR_FAST_API);
        }
    }

    // 처방전을 통해서 리포트를 조회하는 메솓,
    @Transactional // 없을떈 생성하는 메소드를 호출해야하므로 트랜잭션 처리 필수
    public Map<String, ?> getReportByPrescription(Long userId, Long prescriptionId) {
        Report report = reportRepository // 리포트 가져옴
                .findByReportPrescription(
                        prescriptionRepository // 처방전을 기준으로
                                .findById(prescriptionId)
                                .orElseThrow(() ->
                                        new CommonException(ErrorCode.NOT_FOUND_RESOURCE))
                )
                .orElseGet(() -> create(prescriptionId));

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
