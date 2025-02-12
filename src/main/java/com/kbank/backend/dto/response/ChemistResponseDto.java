package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Chemist;
import com.kbank.backend.enumerate.Gender;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChemistResponseDto {

    private Long chemistPk;
    private String chemistNm;
    private String chemistNo;
    private String phoneNo;
    private Gender gender;

    public static ChemistResponseDto fromEntity(Chemist chemist) {
        return ChemistResponseDto.builder()
                .chemistPk(chemist.getChemistPk())
                .chemistNm(chemist.getChemistNm())
                .chemistNo(chemist.getChemistNo())
                .phoneNo(chemist.getPhoneNo())
                .gender(chemist.getGender())
                .build();
    }
}
