package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Doctor;
import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Tp;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDto {

    private long doctorPk;
    private String doctorNm;
    private Tp tp;
    private String doctorNo;
    private String phoneNo;
    private Gender gender;
    private Long hospitalId;

    // Doctor 엔티티를 DTO로 변환하는 메서드
    public static DoctorResponseDto toEntity(Doctor doctor) {
        return DoctorResponseDto.builder()
                .doctorPk(doctor.getDoctorPk())
                .doctorNm(doctor.getDoctorNm())
                .tp(doctor.getTp())
                .doctorNo(doctor.getDoctorNo())
                .phoneNo(doctor.getPhoneNo())
                .gender(doctor.getGender())
                .build();
    }

    public static DoctorResponseDto toEntity(Doctor doctor, Long hospitalId) {
        return DoctorResponseDto.builder()
                .doctorPk(doctor.getDoctorPk())
                .doctorNm(doctor.getDoctorNm())
                .tp(doctor.getTp())
                .doctorNo(doctor.getDoctorNo())
                .phoneNo(doctor.getPhoneNo())
                .gender(doctor.getGender())
                .hospitalId(hospitalId)
                .build();
    }
}
