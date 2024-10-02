package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Medicine;
import com.kbank.backend.enumerate.CopaymentRateCd;
import com.kbank.backend.enumerate.Time;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineResponseDto {

    @NotBlank
    private Long medicinePk;
    @NotBlank
    private String medicineNm;
    @NotBlank
    private Long medicineCd;
    @NotBlank
    private Long price;
    @NotBlank
    private String caution;
    @NotBlank
    private String sideEffect;
    @NotBlank
    private String efficacy;
    @NotBlank
    private String unit;
    @NotBlank
    private Time time;
    @NotBlank
    private String imageUrl;
    @NotBlank
    private String method;
    @NotBlank
    private Integer dosePerDay;
    @NotBlank
    private Integer dosePerTime;
    @NotBlank
    private CopaymentRateCd copaymentRateCd;

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
                .time(medicine.getTime())
                .imageUrl(medicine.getImageUrl())
                .copaymentRateCd(medicine.getCopaymentRateCd())
                .build();
    }
}