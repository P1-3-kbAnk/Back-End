package com.kbank.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.MedicineIntake;
import com.kbank.backend.enumerate.Meal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineIntakeResponseDto {

    private Long medInkPk;
    private Meal meal;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate day;
    private Boolean eatSt;
    private Long userId;
    private Long medicineId;
    private Integer intakeCnt;
    private String medicineNm;
    private String caution;



    public static MedicineIntakeResponseDto fromEntity(MedicineIntake medicineIntake) {
        return MedicineIntakeResponseDto.builder()
                .medInkPk(medicineIntake.getMedInkPk())
                .meal(medicineIntake.getMeal())
                .day(medicineIntake.getDay())
                .eatSt(medicineIntake.getEatSt())
                .userId(medicineIntake.getMedInkUser().getUserPk())
                .medicineId(medicineIntake.getMedInkMedicine().getMedicinePk())
                .intakeCnt(medicineIntake.getIntakeCnt())
                .medicineNm(medicineIntake.getMedInkMedicine().getMedicineNm())
                .caution(medicineIntake.getMedInkMedicine().getCaution())
                .build();
    }
}
