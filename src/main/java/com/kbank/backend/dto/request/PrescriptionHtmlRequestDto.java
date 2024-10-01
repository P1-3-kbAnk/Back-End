package com.kbank.backend.dto.request;

import com.kbank.backend.dto.MedicineDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionHtmlRequestDto {

    // 사용자 정보
    @NotBlank
    private Long userPk;

    //처방전 정보
    @NotBlank
    private Integer prescriptionNo;
    @NotBlank
    private Integer duration;
    @NotBlank
    private String description;

    // 질병 정보
    @NotBlank
    private List<Long> diseasePkList;  // 질병 코드 리스트

    // 약 정보
    @Valid
    @NotBlank
    private List<MedicineDto> medicineList;  // 약 정보 리스트

}
