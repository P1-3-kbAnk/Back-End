package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Hospital;
import com.kbank.backend.domain.HospitalBill;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalBillResponseDto {

    private Long hospitalBillPk;
    private Long totalPrice;
    private Long prescriptionId;
    private String hospitalNm;
    private LocalDateTime createYmd;
    private Long hospitalNo;

    public static HospitalBillResponseDto toEntity(HospitalBill hospitalBill, Long prescriptionId) {

        return HospitalBillResponseDto.builder()
                .hospitalBillPk(hospitalBill.getHospitalBillPk())
                .totalPrice(hospitalBill.getTotalPrice())
                .prescriptionId(prescriptionId)
                .hospitalNm(hospitalBill.getHospitalBillHospital().getHospitalNm())
                .createYmd(hospitalBill.getCreateYmd())
                .hospitalNo(hospitalBill.getHospitalBillHospital().getHospitalNo())
                .build();
    }

}
