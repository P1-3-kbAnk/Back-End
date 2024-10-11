package com.kbank.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.Prescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionSummarizeDto {
    private Long prescriptionPk;
    private Integer prescriptionNo;
    private Integer duration;
    private Integer maxDate;
    private String description;
    private Boolean prescriptionSt;
    private String hospitalNm;
    private String hospitalSi;
    private String hospitalGu;
    private String hospitalDong;
    private String hospitalDetailAddress;
    private String pharmacyNm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createYmd;

    public static PrescriptionSummarizeDto fromEntityWithPharmacy(Prescription prescription) {
        return PrescriptionSummarizeDto.builder()
                .prescriptionPk(prescription.getPrescriptionPk())
                .prescriptionNo(prescription.getPrescriptionNo())
                .duration(prescription.getDuration())
                .maxDate(prescription.getMaxDate())
                .description(prescription.getDescription())
                .prescriptionSt(prescription.getPrescriptionSt())
                .createYmd(prescription.getCreateYmd())
                .hospitalNm(prescription.getPreDoctor().getDoctorHospital().getHospitalNm())
                .hospitalDong(prescription.getPreDoctor().getDoctorHospital().getHospitalDong().getDong_nm())
                .hospitalGu(prescription.getPreDoctor().getDoctorHospital().getHospitalDong().getDong_gu_fk().getGu_nm())
                .hospitalSi(prescription.getPreDoctor().getDoctorHospital().getHospitalDong().getDong_gu_fk().getGu_si_fk().getSi_nm())
                .hospitalDetailAddress(prescription.getPreDoctor().getDoctorHospital().getDetailAddress())
                .pharmacyNm(prescription.getPreChemist().getChemistPharmacy().getPharmacyNm())
                .build();
    }

    public static PrescriptionSummarizeDto fromEntity(Prescription prescription) {
        return PrescriptionSummarizeDto.builder()
                .prescriptionPk(prescription.getPrescriptionPk())
                .prescriptionNo(prescription.getPrescriptionNo())
                .duration(prescription.getDuration())
                .maxDate(prescription.getMaxDate())
                .description(prescription.getDescription())
                .prescriptionSt(prescription.getPrescriptionSt())
                .createYmd(prescription.getCreateYmd())
                .hospitalNm(prescription.getPreDoctor().getDoctorHospital().getHospitalNm())
                .hospitalDong(prescription.getPreDoctor().getDoctorHospital().getHospitalDong().getDong_nm())
                .hospitalGu(prescription.getPreDoctor().getDoctorHospital().getHospitalDong().getDong_gu_fk().getGu_nm())
                .hospitalSi(prescription.getPreDoctor().getDoctorHospital().getHospitalDong().getDong_gu_fk().getGu_si_fk().getSi_nm())
                .hospitalDetailAddress(prescription.getPreDoctor().getDoctorHospital().getDetailAddress())
                .build();
    }
}
