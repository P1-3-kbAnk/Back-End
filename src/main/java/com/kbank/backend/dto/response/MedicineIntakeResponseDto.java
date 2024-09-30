package com.kbank.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.MedicineIntake;
import com.kbank.backend.enumerate.Meal;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineIntakeResponseDto {

    private long medInkPk;
    private Meal meal;
    private boolean eatSt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate day;

    public static MedicineIntakeResponseDto toEntity(MedicineIntake medicineIntake) {
        return MedicineIntakeResponseDto.builder()
                .medInkPk(medicineIntake.getMedInkPk())
                .meal(medicineIntake.getMeal())
                .day(medicineIntake.getDay())
                .eatSt(medicineIntake.isEatSt())
                .build();
    }
}
