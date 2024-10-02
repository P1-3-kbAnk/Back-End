package com.kbank.backend.dto.request;

import com.kbank.backend.enumerate.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank
    private String userNm;
    @NotBlank
    private String phoneNo;
    @NotNull
    private Gender gender;
    @NotBlank
    private String firstNo;
    @NotBlank
    private String lastNo;
    @NotBlank
    private String bankNm;
    @NotBlank
    private String accountNo;
    @NotBlank
    private String accountPw;
}