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
    private Prescription pharmacyBillPrescription;
    private Long pharmacyId;

    public static PharmacyBillResponseDto toEntity(PharmacyBill pharmacyBill, Long pharmacyId) {
        return PharmacyBillResponseDto.builder()
                .pharmacyBillPk(pharmacyBill.getPharmacyBillPk())
                .totalPrice(pharmacyBill.getTotalPrice())
                .pharmacyBillPrescription(pharmacyBill.getPharmacyBillPrescription())
                .pharmacyId(pharmacyId)
                .build();
    }
}
