package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Disease;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiseaseResponseDto {

    private Long diseasePk;
    private String diseaseCd;
    private String diseaseNm;

    public static DiseaseResponseDto toEntity(Disease disease) {
        return DiseaseResponseDto.builder()
                .diseasePk(disease.getDiseasePk())
                .diseaseCd(disease.getDiseaseCd())
                .diseaseNm(disease.getDiseaseNm())
                .build();
    }
}
