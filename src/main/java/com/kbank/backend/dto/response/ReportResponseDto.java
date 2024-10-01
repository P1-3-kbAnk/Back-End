package com.kbank.backend.dto.response;


import com.kbank.backend.domain.Report;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportResponseDto {

    private String intakeMethod;
    private String exercise;
    private String food;


    public static ReportResponseDto toEntity(Report report) {
        return ReportResponseDto
                .builder()
                .intakeMethod(report.getIntakeMethod())
                .exercise(report.getExercise())
                .food(report.getFood())
                .build();
    }

}
