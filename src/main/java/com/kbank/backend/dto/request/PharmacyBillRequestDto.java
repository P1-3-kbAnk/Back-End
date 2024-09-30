package com.kbank.backend.dto.request;


import com.kbank.backend.domain.Pharmacy;
import com.kbank.backend.domain.PharmacyBill;
import com.kbank.backend.domain.Prescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyBillRequestDto {

    private long PrescriptionId;// 명확한 변수명으로 변경
    private long PharmacyId;
    private long totalPrice;



    @Builder
    public PharmacyBill toEntity(Prescription prescription, Pharmacy pharmacy) {
        return PharmacyBill.builder()
                .pharmacyBillPrescription(prescription)
                .pharmacyBillPharmacy(pharmacy)
                .totalPrice(totalPrice)
                .build();
    }


}
