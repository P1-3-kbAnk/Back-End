package com.kbank.backend.dto.response;

import com.kbank.backend.domain.PrescriptionInjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionInjectionResponseDto {

    private Long preInjPk;  // 기본 키
    private String injectionNm;  // 주사제 이름
    private Integer dayCnt;  // 아침, 점심, 저녁 값이 0이 아니면 증가하는 필드
    private String method; //용법 (ex/ 식전30분)
    private Integer totalDay;
    private Integer dosePerMorning;
    private Integer dosePerLunch;
    private Integer dosePerDinner;
    // Medicine에서 가져와야 하는 필드
    private String unit;
    private long price;

    public static PrescriptionInjectionResponseDto fromEntity(PrescriptionInjection prescriptionInjection) {

        return PrescriptionInjectionResponseDto.builder()
                .preInjPk(prescriptionInjection.getPreInjPk())
                .injectionNm(prescriptionInjection.getInjectionNm())
                .dayCnt(prescriptionInjection.getDayCnt())
                .method(prescriptionInjection.getMethod())
                .totalDay(prescriptionInjection.getTotalDay())
                .dosePerMorning(prescriptionInjection.getDosePerMorning())
                .dosePerLunch(prescriptionInjection.getDosePerLunch())
                .dosePerDinner(prescriptionInjection.getDosePerDinner())
                .unit(prescriptionInjection.getPreInjInjection().getUnit())
                .price(prescriptionInjection.getPreInjInjection().getPrice())
                .build();
    }
}
