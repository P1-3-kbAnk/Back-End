package com.kbank.backend.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRequestDto {

    @NotNull
    private Long userPk;
    @NotNull
    private Integer prescriptionNo;
    @NotNull
    private Integer duration;
    @NotBlank
    private String description;

    // 질병 정보
    @NotEmpty
    private List<Long> diseasePkList;  // 질병 코드 리스트

    // 약 정보
    @Valid
    private List<MedicineIntakeInfoRequestDto> medicineIntakeInfoList;  // 약 정보 리스트
    @Valid
    private List<InjectionIntakeInfoRequestDto> injectionIntakeInfoList;  // 약 정보 리스트

}
