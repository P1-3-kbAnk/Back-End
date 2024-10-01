package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Pharmacy;
import com.kbank.backend.domain.PharmacyBill;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyBillResponseDto {

    private String pharmacyNm;
    private Long totalPrice;
    private Long pharmacyNo;

    public static PharmacyBillResponseDto toEntity(Pharmacy pharmacy, PharmacyBill pharmacyBill) {
        return PharmacyBillResponseDto
                .builder()
                .pharmacyNm(pharmacy.getPharmacyNm())
                .totalPrice(pharmacyBill.getTotalPrice())
                .pharmacyNo(pharmacy.getPharmacyNo())
                .build();
    }
}

