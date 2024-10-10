package com.kbank.backend.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRequestDto {

    @NotBlank
    private String userNm;
    @NotBlank
    private String firstNo;
    @NotBlank
    private String lastNo;

    @NotBlank
    private String description;

    // 질병 정보
    @NotEmpty
    private List<Long> diseasePkList;  // 질병 코드 리스트

    // TODO validation custom annotation
    @Valid
    private List<MedicineIntakeInfoRequestDto> medicineIntakeInfoList;
    @Valid
    private List<InjectionIntakeInfoRequestDto> injectionIntakeInfoList;

}
