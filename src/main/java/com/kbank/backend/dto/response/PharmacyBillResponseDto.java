package com.kbank.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.PharmacyBill;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createYmd;
    private Long pharmacyNo;


    public static PharmacyBillResponseDto fromEntity(PharmacyBill pharmacyBill, Long prescriptionId) {

        return PharmacyBillResponseDto.builder()
                .pharmacyBillPk(pharmacyBill.getPharmacyBillPk())
                .totalPrice(pharmacyBill.getTotalPrice())
                .PrescriptionId(prescriptionId)
                .pharmacyNm(pharmacyBill.getPharmacyBillPharmacy().getPharmacyNm())
                .createYmd(pharmacyBill.getCreateYmd())
                .pharmacyNo(pharmacyBill.getPharmacyBillPharmacy().getPharmacyNo())
                .build();
    }
}
