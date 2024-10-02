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

    @NotNull
    private Long hospitalPk;
    @NotBlank
    private String doctorNm;
    @NotNull
    private Tp tp;
    @NotBlank
    private String doctorNo;
    @NotBlank
    private String phoneNo;
    @NotNull
    private Gender gender;

}
