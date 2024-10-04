package com.kbank.backend.dto.response;

import com.kbank.backend.domain.InjectionIntake;
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
public class InjectionIntakeResponseDto {

    private Long injInkPk;
    private Meal meal;
    private Boolean eatSt;
    private LocalDate day;
    private Long userId;
    private Long injectionId;

    public static InjectionIntakeResponseDto toEntity(InjectionIntake injectionIntake, Long userId, Long injectionId) {
        return InjectionIntakeResponseDto.builder()
                .injInkPk(injectionIntake.getInjInkPk())
                .meal(injectionIntake.getMeal())
                .eatSt(injectionIntake.getEatSt())
                .day(injectionIntake.getDay())
                .userId(userId)
                .injectionId(injectionId)
                .build();
    }
}
