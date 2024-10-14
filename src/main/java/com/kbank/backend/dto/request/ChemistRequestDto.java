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
    private String userNm;
    @NotBlank
    private String phoneNo;
    //    @NotNull
//    private Gender gender;
    @NotBlank
    private String firstNo;
    @NotBlank
    private String lastNo;
    @NotBlank
    private String chemistNo;
//    @NotNull
//    private Gender gender;
    @NotBlank
    private String pharmacyDong;
    @NotBlank
    private String pharmacyNm;
    @NotBlank
    private String pharmacyPhoneNo;
    @NotBlank
    private String pharmacyDetailAddress;
}
