package com.kbank.backend.dto.request;

import com.kbank.backend.enumerate.Meal;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineIntakeRequestDto {

    @NotNull
    private Meal meal;

    @NotNull
    private boolean eatSt;

    @NotNull
    private Long medicineId; // Medicine 객체 대신 ID로 받음
}