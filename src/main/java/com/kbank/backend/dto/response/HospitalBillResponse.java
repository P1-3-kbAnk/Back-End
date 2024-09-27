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
public class HospitalBillResponse {

    private long PrescriptionFK;
    private long HospitalFK;

    private long totalPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime billYmd;


    @Builder
    public HospitalBillResponse(HospitalBill hospitalBill) {
        this.PrescriptionFK = hospitalBill.getBillPrescriptionFk().getPrescriptionPk();
        this.HospitalFK = hospitalBill.getHospitalBillHospitalFk().getHospitalPk();
        this.totalPrice = hospitalBill.getTotalPrice();
        this.billYmd = hospitalBill.getBillYmd();
    }


    public HospitalBillResponse(Optional<HospitalBill> hospitalBill) {
    }

    public HospitalBillResponse(HospitalBillResponse hospitalBillResponse) {
    }
}
