package com.kbank.backend.dto.response;


import com.kbank.backend.domain.Disease;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access =AccessLevel.PACKAGE)
public class DiseaseResponseDto {
    private long diseasePrescription;
    private String diseaseCd;

    public static DiseaseResponseDto toEntity(Disease disease) {
        return DiseaseResponseDto
                .builder()
                .diseasePrescription(disease.getDiseasePrescription().getPrescriptionPk())
                .diseaseCd(disease.getDiseaseCd())
                .build();
    }
}
