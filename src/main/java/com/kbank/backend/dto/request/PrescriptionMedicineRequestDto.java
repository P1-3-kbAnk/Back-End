package com.kbank.backend.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionMedicineRequestDto {
    private int totalDays; // 총 투약 일수
    private Long prescriptionId; // 처방전 ID
    private Long medicineId; // 약 정보 ID

    // Getters and setters
}
