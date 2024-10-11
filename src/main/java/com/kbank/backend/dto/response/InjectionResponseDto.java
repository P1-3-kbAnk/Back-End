package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Injection;
import com.kbank.backend.enumerate.CopaymentRateCd;
import com.kbank.backend.enumerate.Time;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InjectionResponseDto {

    private Long injectionPk;
    private String injectionNm;
    private Long injectionCd;
    private Long price;
    private String sideEffect;
    private String caution;
    private String unit;
    private Time time;
    private String efficacy;
    private CopaymentRateCd copaymentRateCd;

    public static InjectionResponseDto fromEntity(Injection injection) {
        return InjectionResponseDto.builder()
                .injectionPk(injection.getInjectionPk())
                .injectionNm(injection.getInjectionNm())
                .injectionCd(injection.getInjectionCd())
                .price(injection.getPrice())
                .sideEffect(injection.getSideEffect())
                .caution(injection.getCaution())
                .unit(injection.getUnit())
                .time(injection.getTime())
                .efficacy(injection.getEfficacy())
                .copaymentRateCd(injection.getCopaymentRateCd())
                .build();
    }
}
