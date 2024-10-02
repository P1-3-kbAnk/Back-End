package com.kbank.backend.domain;

import com.kbank.backend.enumerate.CopaymentRateCd;
import com.kbank.backend.enumerate.Time;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="injection_tb")
public class Injection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="injection_pk")
    private Long injectionPk;

    @Column(name="injection_nm")
    private String injectionNm;

    @Column(name="injection_cd")
    private Long injectionCd;

    @Column(name="price")
    private Long price;

    @Column(name="side_effect")
    private String sideEffect;

    @Column(name="caution")
    private String caution;

    @Column(name="unit")
    private String unit;

    @Enumerated(EnumType.STRING) // EnumType.STRING을 사용하면 문자열로 저장
    @Column(name = "time")
    private Time time;

    @Column(name="efficacy")
    private String efficacy;

    @Enumerated(EnumType.STRING)
    @Column(name="copayment_rate_cd")
    private CopaymentRateCd copaymentRateCd;

    @Builder
    public Injection(String caution, String injectionNm, Long injectionCd, Long price, String sideEffect, String unit, CopaymentRateCd copaymentRateCd, Time time, String efficacy) {
        this.injectionNm = injectionNm;
        this.injectionCd = injectionCd;
        this.price = price;
        this.sideEffect = sideEffect;
        this.unit = unit;
        this.time = time;
        this.efficacy = efficacy;
        this.copaymentRateCd=copaymentRateCd;
        this.caution = caution;
    }
}
