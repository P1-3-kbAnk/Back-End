package com.kbank.backend.dto.request;


import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.Report;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportRequestDto {

    @NotBlank
    private List<String> caution;

    @NotBlank
    private List<String> exercise;

    @NotBlank
    private List<String> food;

    public Report toEntity(Prescription prescription) {

        return Report
                .builder()
                .reportPrescription(prescription)
                .intakeMethod(String.join(", ", caution))
                .exercise(String.join(", ", exercise))
                .food(String.join(", ", food))
                .build();
    }

}
