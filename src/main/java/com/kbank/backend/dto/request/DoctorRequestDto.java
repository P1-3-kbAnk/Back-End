package com.kbank.backend.dto.request;

import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Tp;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDto {

    @NotBlank
    private Long hospitalPk;
    @NotBlank
    private String doctorNm;
    @NotBlank
    private Tp tp;
    @NotBlank
    private String doctorNo;
    @NotBlank
    private String phoneNo;
    @NotBlank
    private Gender gender;

}
