package com.kbank.backend.dto.response;

import com.kbank.backend.domain.HospitalBill;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalBillResponseDto {

    private Long hospitalBillPk;
    private Long totalPrice;
    private Long prescriptionId;
    private Long hospitalId;

    public static HospitalBillResponseDto toEntity(HospitalBill hospitalBill, Long prescriptionId, Long hospitalId) {
        return HospitalBillResponseDto.builder()
                .hospitalBillPk(hospitalBill.getHospitalBillPk())
                .totalPrice(hospitalBill.getTotalPrice())
                .prescriptionId(prescriptionId)
                .hospitalId(hospitalId)
                .build();
    }
}
