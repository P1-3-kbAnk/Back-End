package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Pharmacy;
import com.kbank.backend.domain.PharmacyBill;
import com.kbank.backend.domain.Prescription;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PharmacyBillResponseDto {

    private String PharmacyNm;
    private long totalPrice;
    private LocalDateTime createYmd;
    private long PharmacyNo;


    @Builder
    public PharmacyBillResponseDto(String pharmacyNm, long totalPrice, LocalDateTime createYmd, long pharmacyNo) {
        this.PharmacyNm = pharmacyNm;
        this.totalPrice = totalPrice;
        this.createYmd = createYmd;
        this.PharmacyNo = pharmacyNo;
    }

    public static PharmacyBillResponseDto toEntity(Pharmacy pharmacy, PharmacyBill pharmacyBill) {
        return PharmacyBillResponseDto
                .builder()
                .pharmacyNm(pharmacy.getPharmacyNm())
                .totalPrice(pharmacyBill.getTotalPrice())
                .createYmd(pharmacyBill.getCreateYmd())
                .pharmacyNo(pharmacy.getPharmacyNo())
                .build();
    }



}
