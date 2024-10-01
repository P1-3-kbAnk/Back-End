package com.kbank.backend.domain;


/*
제목 : 주사제 정보 테이블 엔티티 정의
설명 : 주사제 정보를 담은 엔티티.
      주사제 이름, 주사제 코드, 가격, 유의사항, 용량 단위, 효능, 원내조제 및 원외처방 여부로 구성 됨.
담당자 : 임준수
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.enumerate.CopaymentRateCd;
import com.kbank.backend.enumerate.Rate;
import com.kbank.backend.enumerate.Time;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;

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
    //부작용 및 주의사항
    @Column(name="side_effect")
    private String sideEffect;

    @Column(name="unit")
    private String unit;

    @Enumerated(EnumType.STRING) // EnumType.STRING을 사용하면 문자열로 저장
    @Column(name = "time")
    private Time time;

    @Column(name="efficacy")
    private String efficacy;

    @Enumerated(EnumType.STRING) // EnumType.STRING을 사용하면 문자열로 저장
    @Column(name="copayment_rate_cd")
    private CopaymentRateCd copaymentRateCd;

    @Builder
    public Injection(String injectionNm, long injectionCd, Long price, String sideEffect, String unit, CopaymentRateCd copaymentRateCd, Time time, String efficacy) {
        this.injectionNm = injectionNm;
        this.injectionCd = injectionCd;
        this.price = price;
        this.sideEffect = sideEffect;
        this.unit = unit;
        this.time = time;
        this.efficacy = efficacy;
        this.copaymentRateCd=copaymentRateCd;
    }
}
