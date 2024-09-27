package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Disease;
import com.kbank.backend.domain.Prescription;
import lombok.*;

import java.util.Optional;
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiseaseResponse {

    private long diseasePk;
    private Prescription diseasePrescriptionFk;
    private String diseaseCd;


    @Builder
    public DiseaseResponse(Prescription diseasePrescriptionFk, String diseaseCd) {
        this.diseasePrescriptionFk = diseasePrescriptionFk;
        this.diseaseCd = diseaseCd;
    }

    public DiseaseResponse(Optional<Disease> disease) {
    }

    public DiseaseResponse(Disease disease) {
    }
}
