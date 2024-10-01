package com.kbank.backend.dto.request;

import com.kbank.backend.domain.Medicine;
import com.kbank.backend.domain.MedicineIntake;
import com.kbank.backend.domain.User;
import com.kbank.backend.enumerate.Meal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineIntakeRequest {

    private long medInkUserID;
    private long medInkMedicineFk;

    private LocalDate day;
    private Meal meal;
    private boolean eatSt;

    public MedicineIntake toEntity(User user, Medicine medicine) {
        return MedicineIntake.builder()
                .meal(meal)
                .day(day)
                .eatSt(eatSt)
                .user(user)
                .medicine(medicine)
                .build();
    }

}
