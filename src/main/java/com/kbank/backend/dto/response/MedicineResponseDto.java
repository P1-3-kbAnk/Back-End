package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Medicine;
import com.kbank.backend.enumerate.CopaymentRateCd;
import com.kbank.backend.enumerate.Time;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineResponseDto {

    private Long medicinePk;
    private String medicineNm;
    private Long medicineCd;
    private Long price;
    private String caution;
    private String sideEffect;
    private String efficacy;
    private String unit;
    private CopaymentRateCd copaymentRateCd;
    private Time time;
    private String imageUrl;

    public static MedicineResponseDto toEntity(Medicine medicine) {
        return MedicineResponseDto.builder()
                .medicinePk(medicine.getMedicinePk())
                .medicineNm(medicine.getMedicineNm())
                .medicineCd(medicine.getMedicineCd())
                .price(medicine.getPrice())
                .caution(medicine.getCaution())
                .sideEffect(medicine.getSideEffect())
                .efficacy(medicine.getEfficacy())
                .unit(medicine.getUnit())
                .copaymentRateCd(medicine.getCopaymentRateCd())
                .time(medicine.getTime())
                .imageUrl(medicine.getImageUrl())
                .build();
    }
}
