package com.kbank.backend.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineTimeDto {
    @NotNull
    private LocalTime morningAlarm;
    @NotNull
    private LocalTime lunchAlarm;
    @NotNull
    private LocalTime dinnerAlarm;
}



