package com.kbank.backend.dto.request;

import com.kbank.backend.enumerate.Meal;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineIntakeRequestDto {

    @NotBlank
    private Long medicineId;
    @NotBlank
    private Meal meal;
    @NotBlank
    private Boolean eatSt;
}