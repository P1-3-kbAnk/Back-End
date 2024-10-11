package com.kbank.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.Prescription;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createYmd;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime prescribeYmd;

    public static PrescriptionResponseDto fromEntity(Prescription prescription) {
        return PrescriptionResponseDto.builder()
                .prescriptionPk(prescription.getPrescriptionPk())
                .prescriptionNo(prescription.getPrescriptionNo())
                .duration(prescription.getDuration())
                .description(prescription.getDescription())
                .prescriptionSt(prescription.getPrescriptionSt())
                .insuranceSt(prescription.getInsuranceSt())
                .createYmd(prescription.getCreateYmd())
                .prescribeYmd(prescription.getPrescribeYmd())
                .build();
    }
}
