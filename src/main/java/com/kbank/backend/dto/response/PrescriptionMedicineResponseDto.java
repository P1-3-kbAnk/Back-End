package com.kbank.backend.dto.response;


import com.kbank.backend.domain.PrescriptionMedicine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionMedicineResponseDto {

    private Long preMedPk;
    private String medicineNm;
    private Integer dayCnt;  // 아침, 점심, 저녁 값이 0이 아니면 증가하는 필드
    private String method; //용법 (ex/ 식전30분)
    private Integer totalDay;
    private Integer dosePerMorning;
    private Integer dosePerLunch;
    private Integer dosePerDinner;
    // Medicine에서 가져와야 하는 필드
    private String unit;
    private long price;

    public static PrescriptionMedicineResponseDto fromEntity(PrescriptionMedicine prescriptionMedicine) {

        return PrescriptionMedicineResponseDto.builder()
                .preMedPk(prescriptionMedicine.getPreMedPk())
                .medicineNm(prescriptionMedicine.getMedicineNm())
                .dayCnt(prescriptionMedicine.getDayCnt())  // 계산된 dayCnt 값을 설정
                .method(prescriptionMedicine.getMethod())
                .totalDay(prescriptionMedicine.getTotalDay())
                .dosePerMorning(prescriptionMedicine.getDosePerMorning())
                .dosePerLunch(prescriptionMedicine.getDosePerLunch())
                .dosePerDinner(prescriptionMedicine.getDosePerDinner())
                .unit(prescriptionMedicine.getPreMedMedicine().getUnit())
                .price(prescriptionMedicine.getPreMedMedicine().getPrice())
                .build();
    }
}
