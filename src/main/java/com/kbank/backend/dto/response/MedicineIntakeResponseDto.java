package com.kbank.backend.dto.response;

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
    private LocalDate day;
    private Boolean eatSt;
    private Long userId;
    private Long medicineId;

    public static MedicineIntakeResponseDto toEntity(MedicineIntake medicineIntake, Long userId, Long medicineId) {
        return MedicineIntakeResponseDto.builder()
                .medInkPk(medicineIntake.getMedInkPk())
                .meal(medicineIntake.getMeal())
                .day(medicineIntake.getDay())
                .eatSt(medicineIntake.getEatSt())
                .userId(userId)
                .medicineId(medicineId)
                .build();
    }
}
