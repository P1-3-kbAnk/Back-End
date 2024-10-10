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
    private String hospitalNm;
    private String pharmacyNm;
    private Boolean prescriptionSt;
    private Boolean insuranceSt;
    private Long doctorId;
    private Long userId;
    private Long chemistId;

    public static PrescriptionResponseDto toEntity(Prescription prescription) {
        return PrescriptionResponseDto.builder()
                .prescriptionPk(prescription.getPrescriptionPk())
                .prescriptionNo(prescription.getPrescriptionNo())
                .duration(prescription.getDuration())
                .description(prescription.getDescription())
                .prescriptionSt(prescription.getPrescriptionSt())
                .insuranceSt(prescription.getInsuranceSt())
                .build();
    }

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

    public static PrescriptionResponseDto fromEntityWithNm(Prescription prescription) {
        return PrescriptionResponseDto.builder()
                .prescriptionPk(prescription.getPrescriptionPk())
                .prescriptionNo(prescription.getPrescriptionNo())
                .duration(prescription.getDuration())
                .description(prescription.getDescription())
                .prescriptionSt(prescription.getPrescriptionSt())
                .insuranceSt(prescription.getInsuranceSt())
                .hospitalNm(prescription.getPreDoctor().getDoctorHospital().getHospitalNm())
                .doctorId(prescription.getPreDoctor().getDoctorPk())
                .pharmacyNm(prescription.getPreChemist().getChemistPharmacy().getPharmacyNm())
                .chemistId(prescription.getPreChemist().getChemistPk())
                .build();
    }
}
