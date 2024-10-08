package com.kbank.backend.dto.response;

import com.kbank.backend.domain.PharmacyBill;
import com.kbank.backend.domain.Prescription;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyBillResponseDto {

    private Long pharmacyBillPk;
    private Long totalPrice;
    private long PrescriptionId;
    private String pharmacyNm;

    public static PharmacyBillResponseDto toEntity(PharmacyBill pharmacyBill,Long prescriptionId, String pharmacyNm) {
        return PharmacyBillResponseDto.builder()
                .pharmacyBillPk(pharmacyBill.getPharmacyBillPk())
                .totalPrice(pharmacyBill.getTotalPrice())
                .PrescriptionId(prescriptionId)
                .pharmacyNm(pharmacyNm)
                .build();
    }
}
