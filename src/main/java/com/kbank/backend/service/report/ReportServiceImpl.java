package com.kbank.backend.service.report;


import com.kbank.backend.controller.ReportRestTemplate;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.PrescriptionMedicine;
import com.kbank.backend.domain.Report;
import com.kbank.backend.dto.request.ReportRequest;
import com.kbank.backend.dto.response.ReportResponse;
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
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final DiseaseRepository diseaseRepository;
    private final ReportRestTemplate restTemplate;
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Optional<Report> findById(Long id) {
        return reportRepository.findById(id);
    }

    @Override
    public Optional<Report> findByPrescription(Prescription prescription) {
        return reportRepository.findByReportPrescription(prescription);
    }

    @Override
    public List<String> getMedicineList(Prescription prescription){
        return prescriptionMedicineRepository
                .findMedicineByPrescription(prescription);
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

            ReportRequest responseFromFa = restTemplate.getReport(query);
            //System.out.println(responseFromFa);

            Report report = responseFromFa.toEntity(prescription);

            reportRepository.save(responseFromFa.toEntity(prescription));

            return report;

        } catch (CommonException e) {
            throw new CommonException(ErrorCode.ERR_FAST_API);
        }
    }

    public ReportResponse getReportByPrescription(long id) {
        Optional<Report> report = reportRepository
                .findByReportPrescription(
                        prescriptionRepository
                                .findById(id)
                                .orElseThrow(() ->
                                        new CommonException(ErrorCode.NOT_FOUND_RESOURCE))
                );
        if(report.isPresent()){ // 리포트가 존재하면

            return ReportResponse.builder()
                    .intakeMethod(report.get().getIntakeMethod())
                    .food(report.get().getFood())
                    .exercise(report.get().getExercise()).build();

        } else { // 리포트가 없을 때

            Report newReport = create(id);

            return ReportResponse
                    .builder()
                    .intakeMethod(newReport.getIntakeMethod())
                    .food(newReport.getFood())
                    .exercise(newReport.getExercise())
                    .build();
        }
    }
}
