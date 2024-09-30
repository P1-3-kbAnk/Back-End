package com.kbank.backend.dto.response;

import com.kbank.backend.domain.PrescriptionMedicine;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrescriptionMedicineResponseDto {
    private int totalDays; // 총 투약 일수
    private long preMedPrescription;
    private PrescriptionResponseDto prescription; // 처방전 정보


    public static PrescriptionMedicineResponseDto toEntity(PrescriptionMedicine prescriptionMedicine){
        return PrescriptionMedicineResponseDto
                .builder()
                .preMedPrescription(prescriptionMedicine.getPreMedPrescription().getPrescriptionPk())
                .totalDays(prescriptionMedicine.getTotalDays()) //
                .build();
    }
}
