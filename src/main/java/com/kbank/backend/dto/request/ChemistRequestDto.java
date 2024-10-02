package com.kbank.backend.dto.request;

import com.kbank.backend.enumerate.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChemistRequestDto {

    @NotBlank
    private String chemistNm;
    @NotBlank
    private String chemistNo;
    @NotBlank
    private String phoneNo;
    @NotNull
    private Gender gender;
    @NotNull
    private Long pharmacyPk;
}
