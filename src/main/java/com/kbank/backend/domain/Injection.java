package com.kbank.backend.domain;


/*
제목 : 주사제 정보 테이블 엔티티 정의
설명 : 주사제 정보를 담은 엔티티.
      주사제 이름, 주사제 코드, 가격, 유의사항, 용량 단위, 효능, 원내조제 및 원외처방 여부로 구성 됨.
담당자 : 임준수
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.enumerate.Rate;
import com.kbank.backend.enumerate.Time;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="injection_tb")
public class Injection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="injection_pk")
    private long injectionPk;

    @Column(name="injection_nm")
    private String injectionNm;

    @Column(name="injection_cd")
    private long injectionCd;

    @Column(name="price")
    private Long price;

    @Column(name="caution")
    private String caution;

    @Column(name="side_effect")
    private String sideEffect;

    @Column(name="usage")
    private String usage;

    @Column(name="unit")
    private String unit;

    @Column(name="dose_per_time")
    private int dosePerTime;

    @Column(name="dose_per_day")
    private int dosePerDay;

    @Enumerated(EnumType.STRING)
    @Column(name="copayment_rate_cd")
    private Rate copaymentRateCd;

    @Enumerated(EnumType.STRING) // EnumType.STRING을 사용하면 문자열로 저장
    private Time time;

    @Column(name="efficacy")
    private String efficacy;

    @Column(name="method")
    private int method;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDate createYmd;

    @Builder
    public Injection(String injectionNm, long injectionCd, Long price, String caution, String sideEffect, String usage, String unit, int dosePerTime, int dosePerDay, Rate copaymentRateCd, Time time, String efficacy, int method) {
        this.injectionNm = injectionNm;
        this.injectionCd = injectionCd;
        this.price = price;
        this.caution = caution;
        this.sideEffect = sideEffect;
        this.usage = usage;
        this.unit = unit;
        this.dosePerTime = dosePerTime;
        this.dosePerDay = dosePerDay;
        this.copaymentRateCd = copaymentRateCd;
        this.time = time;
        this.efficacy = efficacy;
        this.method = method;
        this.createYmd = LocalDate.now();
    }
}
