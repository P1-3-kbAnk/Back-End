package com.kbank.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.Hospital;
import com.kbank.backend.domain.HospitalBill;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalBillResponseDto {

    private String HospitalNm;
    private long totalPrice;
    private LocalDateTime createYmd;
    private long HospitalNo;


    @Builder
    public HospitalBillResponseDto(String hospitalNm, long totalPrice, LocalDateTime createYmd, long hospitalNo) {
        this.HospitalNm = hospitalNm;
        this.totalPrice = totalPrice;
        this.createYmd = createYmd;
        this.HospitalNo = hospitalNo;
    }

    public static HospitalBillResponseDto toEntity(Hospital hospital, HospitalBill hospitalBill) {
        return HospitalBillResponseDto
                .builder()
                .hospitalNm(hospital.getHospitalNm())
                .totalPrice(hospitalBill.getTotalPrice())
                .createYmd(hospitalBill.getCreateYmd())
                .hospitalNo(hospital.getHospitalNo())
                .build();
    }

}

