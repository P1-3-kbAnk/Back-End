package com.kbank.backend.service;


import com.kbank.backend.controller.ReportRestTemplate;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.Report;
import com.kbank.backend.dto.request.ReportRequestDto;
import com.kbank.backend.dto.response.ReportResponseDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.DiseaseRepository;
import com.kbank.backend.repository.PrescriptionMedicineRepository;
import com.kbank.backend.repository.PrescriptionRepository;
import com.kbank.backend.repository.ReportRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportService {

    private final ReportRepository reportRepository;
    private final DiseaseRepository diseaseRepository;
    private final ReportRestTemplate restTemplate;
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;

    public List<Report> findAll(Long userPk) {
        return reportRepository.findAll();
    }

    public Optional<Report> findById(Long userPk, Long id) {
        return reportRepository.findById(id);
    }

    public Optional<Report> findByPrescription(Long userPk, Prescription prescription) {
        return reportRepository.findByReportPrescription(prescription);
    }

    public List<String> getMedicineList(Prescription prescription){
        List<String> res = new ArrayList<>();
        prescriptionMedicineRepository
                .findMedicineByPrescription(prescription)
                .forEach(e -> res.add(e.getMedicineNm()));

        return res;
    }

    public Report create(long prescriptionId) {
        try {
            Prescription prescription = prescriptionRepository
                    .findById(prescriptionId)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

            List<String> res = new ArrayList<>();
            Map<String, String> query = new HashMap<>();

            diseaseRepository.findByDiseasePrescription(prescription)
                    .forEach(e -> res.add(e.getDiseaseCd()));

            query.put("medi", getMedicineList(prescription).toString());
            query.put("code", res.toString());

            ReportRequestDto responseFromFa = restTemplate.getReport(query);
            //System.out.println(responseFromFa);

            Report report = responseFromFa.toEntity(prescription);

            reportRepository.save(responseFromFa.toEntity(prescription));

            return report;

        } catch (CommonException e) {
            throw new CommonException(ErrorCode.ERR_FAST_API);
        }
    }

    public ReportResponseDto getReportByPrescription(long id) {
        Optional<Report> report = reportRepository
                .findByReportPrescription(
                        prescriptionRepository
                                .findById(id)
                                .orElseThrow(() ->
                                        new CommonException(ErrorCode.NOT_FOUND_RESOURCE))
                );
        if(report.isPresent()){ // 리포트가 존재하면

            return ReportResponseDto.toEntity(report.get());

        } else { // 리포트가 없을 때

            Report newReport = create(id);

            return ReportResponseDto.toEntity(newReport);
        }
    }
}
