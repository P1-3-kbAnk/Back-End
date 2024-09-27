package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Medicine;
import com.kbank.backend.domain.MedicineIntake;
import com.kbank.backend.domain.User;
import com.kbank.backend.enumerate.Meal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MedicineIntakeResponse {

    private long medInkUserFk;
    private long medInkMedicineFk;

    private Meal meal;
    private boolean eatSt;

    public MedicineIntakeResponse(MedicineIntake medicineIntake) {
        this.medInkUserFk = medicineIntake.getMedInkUserFk().getUserPk();
        this.meal = medicineIntake.getMeal();
        this.eatSt = medicineIntake.isEatSt();
    }

    public MedicineIntakeResponse(Optional<MedicineIntake> medicineIntake) {
    }
}
