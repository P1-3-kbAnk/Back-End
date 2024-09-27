package com.kbank.backend.dto.request;

import com.kbank.backend.domain.Medicine;
import com.kbank.backend.domain.MedicineIntake;
import com.kbank.backend.domain.User;
import com.kbank.backend.enumerate.Meal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineIntakeRequest {

    private long medInkUserID;
    private long medInkMedicineFk;

    private Meal meal;
    private boolean eatSt;

    public MedicineIntake toEntity(User user, Medicine medicine) {
        return MedicineIntake.builder()
                .medInkUserFk(user)
                .medInkMedicineFk(medicine)
                .meal(meal)
                .eatSt(eatSt)
                .build();
    }

}
