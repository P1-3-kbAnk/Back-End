package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Pharmacy;
import com.kbank.backend.domain.PharmacyBill;
import com.kbank.backend.domain.Prescription;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyBillResponseDto {

    private Long pharmacyBillPk;
    private Long totalPrice;
    private long PrescriptionId;
    private String pharmacyNm;
    private LocalDateTime createYmd;
    private Long pharmacyNo;

    public static PharmacyBillResponseDto toEntity(Pharmacy pharmacy,PharmacyBill pharmacyBill, Long prescriptionId, String pharmacyNm, LocalDateTime createYMD) {
        return PharmacyBillResponseDto.builder()
                .pharmacyBillPk(pharmacyBill.getPharmacyBillPk())
                .totalPrice(pharmacyBill.getTotalPrice())
                .PrescriptionId(prescriptionId)
                .pharmacyNm(pharmacyNm)
                .createYmd(createYMD)
                .pharmacyNo(pharmacy.getPharmacyNo())
                .build();
    }
}
