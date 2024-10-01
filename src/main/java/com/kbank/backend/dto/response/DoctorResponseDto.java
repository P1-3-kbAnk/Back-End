package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Doctor;
import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Tp;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDto {
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

    public static DoctorResponseDto toEntity(final Doctor doctor) {
        return DoctorResponseDto.builder()
                .doctorNm(doctor.getDoctorNm())
                .tp(doctor.getTp())
                .doctorNo(doctor.getDoctorNo())
                .phoneNo(doctor.getPhoneNo())
                .gender(doctor.getGender())
                .build();
    }
}