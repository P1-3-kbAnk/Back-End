package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Hospital;
import com.kbank.backend.domain.HospitalBill;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access =AccessLevel.PACKAGE)
public class HospitalBillResponseDto {

    private String hospitalNm;
    private Long totalPrice;
    private Long hospitalNo;

    public static HospitalBillResponseDto toEntity(Hospital hospital, HospitalBill hospitalBill) {
        return HospitalBillResponseDto
                .builder()
                .hospitalNm(hospital.getHospitalNm())
                .totalPrice(hospitalBill.getTotalPrice())
                .hospitalNo(hospital.getHospitalNo())
                .build();
    }

}

