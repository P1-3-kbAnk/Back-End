package com.kbank.backend.dto;


import com.kbank.backend.domain.Medicine;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineDto {

    private String medicineNm;
    private int doesPerTime;
    private int doesPerDay;
    private String usage;

    @Builder
    public MedicineDto(String medicineNm, int doesPerTime, int doesPerDay, String usage) {
        this.medicineNm = medicineNm;
        this.doesPerTime = doesPerTime;
        this.doesPerDay = doesPerDay;
        this.usage = usage;
    }

    public static MedicineDto toEntity(Medicine medicine) {
        return MedicineDto
                .builder()
                .medicineNm(medicine.getMedicineNm())
                .doesPerTime(medicine.getDosePerTime())
                .doesPerDay(medicine.getDosePerDay())
                .usage(medicine.getMethod())
                .build();
    }

}
