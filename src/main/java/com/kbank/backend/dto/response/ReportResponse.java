package com.kbank.backend.dto.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReportResponse {

    private String intakeMethod;
    private String exercise;
    private String food;

    @Builder
    public ReportResponse(String intakeMethod, String exercise, String food) {
        this.intakeMethod = intakeMethod;
        this.exercise = exercise;
        this.food = food;
    }

}
