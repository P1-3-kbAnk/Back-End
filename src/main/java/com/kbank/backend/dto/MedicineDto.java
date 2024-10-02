package com.kbank.backend.dto;


import com.kbank.backend.domain.Medicine;
import com.kbank.backend.enumerate.CopaymentRateCd;
import com.kbank.backend.enumerate.Time;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자 생성
@AllArgsConstructor
public class MedicineDto {

    private String medicineNm;
    private int doesPerTime;
    private int doesPerDay;
    private String usage;

    private String method;
    private Long medicineCd;
    private Long price;
    private String caution;
    private String sideEffect;
    private String efficacy;
    private String unit;
    private Time time;
    private String imageUrl;
    private CopaymentRateCd copaymentRateCd; // 추가 필드

    @Builder
    public MedicineDto(String medicineNm, int doesPerTime, int doesPerDay, String usage) {
        this.medicineNm = medicineNm;
        this.doesPerTime = doesPerTime;
        this.doesPerDay = doesPerDay;
        this.usage = usage;  ///이거 아마 method로 바꼈어요
    }

    public static MedicineDto toEntity(Medicine medicine) {
        return MedicineDto
                .builder()
                .medicineNm(medicine.getMedicineNm())
                .build();
    }

}
