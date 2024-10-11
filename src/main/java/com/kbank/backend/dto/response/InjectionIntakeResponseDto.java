package com.kbank.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate day;
    private Long userId;
    private Long injectionId;
    private String injectionNm;
    private String sideEffect;


    public static InjectionIntakeResponseDto toEntity(InjectionIntake injectionIntake) {
        return InjectionIntakeResponseDto.builder()
                .injInkPk(injectionIntake.getInjInkPk())
                .meal(injectionIntake.getMeal())
                .eatSt(injectionIntake.getEatSt())
                .day(injectionIntake.getDay())
                .userId(injectionIntake.getInjInkUser().getUserPk())
                .injectionId(injectionIntake.getInjInkInjection().getInjectionPk())
                .injectionNm(injectionIntake.getInjInkInjection().getInjectionNm())
                .sideEffect(injectionIntake.getInjInkInjection().getSideEffect())
                .build();
    }
}
