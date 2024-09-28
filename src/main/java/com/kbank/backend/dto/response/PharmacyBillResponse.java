package com.kbank.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.HospitalBill;
import com.kbank.backend.domain.PharmacyBill;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PharmacyBillResponse {

    private long PrescriptionFK;
    private long PharmacyFK;

    private long totalPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime billYmd;



    @Builder
    public PharmacyBillResponse(PharmacyBill pharmacyBill) {
        this.PrescriptionFK = pharmacyBill.getBillPrescriptionFk().getPrescriptionPk();
        this.PharmacyFK = pharmacyBill.getPharmacyBillPharmacyFk().getPharmacyPk();
        this.totalPrice = pharmacyBill.getTotalPrice();
        this.billYmd = pharmacyBill.getBillYmd();


    }

    public PharmacyBillResponse(Optional<PharmacyBill> pharmacyBill) {
    }

    public PharmacyBillResponse(PharmacyBillResponse pharmacyBillResponse) {
    }
}