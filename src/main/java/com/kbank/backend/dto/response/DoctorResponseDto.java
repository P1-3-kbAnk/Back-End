package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Doctor;
import com.kbank.backend.enumerate.Gender;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDto {

    private long doctorPk;
    private String doctorNm;
    private String tp;
    private String doctorNo;
    private String phoneNo;
    private Gender gender;
    private Long hospitalId;

    public static DoctorResponseDto fromEntity(Doctor doctor) {
        return DoctorResponseDto.builder()
                .doctorPk(doctor.getDoctorPk())
                .doctorNm(doctor.getDoctorNm())
                .tp(doctor.getTp().name())
                .doctorNo(doctor.getDoctorNo())
                .phoneNo(doctor.getPhoneNo())
                .gender(doctor.getGender())
                .build();
    }

    public static DoctorResponseDto fromEntity(Doctor doctor, Long hospitalId) {
        return DoctorResponseDto.builder()
                .doctorPk(doctor.getDoctorPk())
                .doctorNm(doctor.getDoctorNm())
                .tp(doctor.getTp().name())
                .doctorNo(doctor.getDoctorNo())
                .phoneNo(doctor.getPhoneNo())
                .gender(doctor.getGender())
                .hospitalId(hospitalId)
                .build();
    }
}
