package com.kbank.backend.dto.request;

import com.kbank.backend.enumerate.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotNull
    private String userNm;

    @NotNull
    private String phoneNo;

    @NotNull
    private Gender gender;

    @NotNull
    private String firstNo;

    @NotNull
    private String lastNo;

    @NotNull
    private String bankNm;

    @NotNull
    private String accountNo;

    @NotNull
    private String accountPw;
}