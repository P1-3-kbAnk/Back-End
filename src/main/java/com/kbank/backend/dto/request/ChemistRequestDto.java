package com.kbank.backend.dto.request;

import com.kbank.backend.enumerate.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChemistRequestDto {

    @NotNull
    private String chemistNm;

    @NotNull
    private String chemistNo;

    @NotNull
    private String phoneNo;

    @NotNull
    private Gender gender;

    @NotNull
    private Long pharmacyPk;
}
