package com.kbank.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionMedicineRequestDto {

    @NotBlank
    private Integer totalDays; // 총 투약 일수
    @NotBlank
    private Long prescriptionId; // 처방전 ID
    @NotBlank
    private Long medicineId; // 약 정보 ID

}
