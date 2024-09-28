package com.kbank.backend.dto.request;

import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Tp;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDto {

    @NotNull
    private String doctorNm;

    @NotNull
    private Tp tp;

    @NotNull
    private String doctorNo;

    @NotNull
    private String phoneNo;

    @NotNull
    private Gender gender;

    @NotNull
    private Long hospitalPk;
}
