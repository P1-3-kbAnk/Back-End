package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Prescription;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrescriptionResponse {
    private long preDoctorFk;
    private long preUserFk;
    private long preChemistFk;
    private LocalDateTime createYmd;
    private int prescriptionNo;
    private int duration;
    private String description;
    private boolean prescriptionSt;
    private boolean insuranceSt;

    @Builder
    public PrescriptionResponse(Prescription prescription){
        this.preDoctorFk = prescription.getPreDoctorFk().getDoctorPk();  // 외래 키 대신 엔티티의 PK 사용
        this.preUserFk = prescription.getPreUserFk().getUserPk();  // 외래 키 대신 엔티티의 PK 사용
        this.preChemistFk = prescription.getPreChemistFk().getChemistPk();  // 외래 키 대신 엔티티의 PK 사용
        this.createYmd = prescription.getCreateYmd();
        this.prescriptionNo = prescription.getPrescriptionNo();
        this.duration = prescription.getDuration();
        this.description = prescription.getDescription();
        this.prescriptionSt = prescription.isPrescriptionSt();
        this.insuranceSt = prescription.isInsuranceSt();
    }
}
