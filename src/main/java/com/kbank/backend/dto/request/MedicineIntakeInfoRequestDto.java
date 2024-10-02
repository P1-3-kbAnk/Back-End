package com.kbank.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineIntakeInfoRequestDto {

    @NotNull
    private Long medicinePk;
    @NotNull
    private Integer totalDay;
    @NotNull
    private Integer dosePerMorning;
    @NotNull
    private Integer dosePerLunch;
    @NotNull
    private Integer dosePerDinner;
    @NotBlank
    private String method;
}
