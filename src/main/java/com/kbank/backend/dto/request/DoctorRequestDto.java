package com.kbank.backend.dto.request;

import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Tp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDto {

    @NotBlank
    private String userNm;
    @NotNull
    private Tp tp;
    @NotBlank
    private String doctorNo;
    @NotBlank
    private String phoneNo;
    @NotBlank
    private String firstNo;
    @NotBlank
    private String lastNo;
    @NotBlank
    private String licenseNo;
    @NotBlank
    private String hospitalDong;

    @NotBlank
    private String hospitalNm;
    @NotBlank
    private String hospitalPhoneNo;
    @NotBlank
    private String hospitalDetailAddress;

}
