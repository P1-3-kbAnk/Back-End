package com.kbank.backend.dto.request;

import com.kbank.backend.enumerate.Gender;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private Gender gender;
    @NotBlank
    private Long pharmacyPk;
}
