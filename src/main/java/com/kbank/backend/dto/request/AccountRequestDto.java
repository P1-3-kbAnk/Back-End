package com.kbank.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {

    @NotBlank
    private String bankNm;
    @NotBlank
    private String accountNo;

}
