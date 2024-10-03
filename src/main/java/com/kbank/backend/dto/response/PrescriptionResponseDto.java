package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Prescription;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionResponseDto {

    private Long prescriptionPk;
    private Integer prescriptionNo;
    private Integer duration;
    private String description;
    private Boolean prescriptionSt;
    private Boolean insuranceSt;
    private Long doctorId;
    private Long userId;
    private Long chemistId;

    public static PrescriptionResponseDto toEntity(Prescription prescription, Long userId, Long doctorId, Long chemistId) {
        return PrescriptionResponseDto.builder()
                .prescriptionPk(prescription.getPrescriptionPk())
                .prescriptionNo(prescription.getPrescriptionNo())
                .duration(prescription.getDuration())
                .description(prescription.getDescription())
                .prescriptionSt(prescription.getPrescriptionSt())
                .insuranceSt(prescription.getInsuranceSt())
                .userId(userId)
                .doctorId(doctorId)
                .chemistId(chemistId)
                .build();
    }
}
