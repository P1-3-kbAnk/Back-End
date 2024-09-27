package com.kbank.backend.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.HospitalBill;
import com.kbank.backend.domain.Pharmacy;
import com.kbank.backend.domain.PharmacyBill;
import com.kbank.backend.domain.Prescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyBillRequest {

    private long PrescriptionId;// 명확한 변수명으로 변경
    private long PharmacyId;

    private long totalPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime billYmd;



    @Builder
    public PharmacyBill toEntity(Prescription prescription, Pharmacy pharmacy) {
        return PharmacyBill.builder()
                .billPrescriptionFk(prescription)
                .pharmacyBillPharmacyFk(pharmacy)
                .totalPrice(totalPrice)
                .billYmd(billYmd)
                .build();
    }


}
