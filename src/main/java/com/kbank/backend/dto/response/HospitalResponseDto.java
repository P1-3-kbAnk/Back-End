package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Hospital;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalResponseDto {

    private Long hospitalPk;
    private String hospitalNm;
    private String phoneNo;
    private Long hospitalNo;
    private String faxNo;
    private Long dongId;

    public static HospitalResponseDto fromEntity(Hospital hospital) {
        return HospitalResponseDto.builder()
                .hospitalPk(hospital.getHospitalPk())
                .hospitalNm(hospital.getHospitalNm())
                .phoneNo(hospital.getPhoneNo())
                .hospitalNo(hospital.getHospitalNo())
                .faxNo(hospital.getFaxNo())
                .build();
    }

    public static HospitalResponseDto fromEntity(Hospital hospital, Long dongId) {
        return HospitalResponseDto.builder()
                .hospitalPk(hospital.getHospitalPk())
                .hospitalNm(hospital.getHospitalNm())
                .phoneNo(hospital.getPhoneNo())
                .hospitalNo(hospital.getHospitalNo())
                .faxNo(hospital.getFaxNo())
                .dongId(dongId)
                .build();
    }
}
