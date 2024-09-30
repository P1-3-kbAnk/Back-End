package com.kbank.backend.dto.response;


import com.kbank.backend.domain.Report;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReportResponseDto {

    private String intakeMethod;
    private String exercise;
    private String food;

    @Builder
    public ReportResponseDto(String intakeMethod, String exercise, String food) {
        this.intakeMethod = intakeMethod;
        this.exercise = exercise;
        this.food = food;
    }

    public static ReportResponseDto toEntity(Report report) {
        return ReportResponseDto
                .builder()
                .intakeMethod(report.getIntakeMethod())
                .exercise(report.getExercise())
                .food(report.getFood())
                .build();
    }

}
