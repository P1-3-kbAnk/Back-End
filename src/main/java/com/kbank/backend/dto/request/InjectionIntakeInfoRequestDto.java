package com.kbank.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InjectionIntakeInfoRequestDto {

    @NotNull
    private Long injectionPk;
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
