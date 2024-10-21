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
@Table(name = "injection_tb")
public class Injection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "injection_pk", nullable = false, updatable = false, unique = true)
    private Long injectionPk;

    @Column(name = "injection_nm", nullable = false)
    private String injectionNm;

    @Column(name = "injection_cd", nullable = false)
    private Long injectionCd;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "side_effect", nullable = false)
    private String sideEffect;

    @Column(name = "caution", nullable = false)
    private String caution;

    @Column(name = "unit", nullable = false)
    private String unit;

    @Enumerated(EnumType.STRING) // EnumType.STRING을 사용하면 문자열로 저장
    @Column(name = "time")
    private Time time;

    @Column(name = "efficacy", nullable = false)
    private String efficacy;

    @Enumerated(EnumType.STRING)
    @Column(name = "copayment_rate_cd", nullable = false)
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
        this.copaymentRateCd = copaymentRateCd;
        this.caution = caution;
    }
}
