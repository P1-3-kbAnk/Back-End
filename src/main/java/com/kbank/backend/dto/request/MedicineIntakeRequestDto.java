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
    private Long medicineId;
    @NotNull
    private Meal meal;
    @NotNull
    private Boolean eatSt;
}