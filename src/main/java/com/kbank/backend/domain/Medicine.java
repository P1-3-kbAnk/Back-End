package com.kbank.backend.domain;


/*
제목 : 복약 정보 테이블 엔티티 정의
설명 : 약에 대한 정보를 담은 엔티티
담당자 : 김성헌
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.enumerate.CopaymentRateCd;
import com.kbank.backend.enumerate.Time;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="medicine_tb")
public class Medicine {

    @Id
    @Column(name = "medicine_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private Long medicinePk;

    @Column(name="medicine_nm")
    private String medicineNm;

    @Column(name="medicine_cd")
    private Long medicineCd;

    @Column(name="price")
    private Long price;

    @Column(name="caution")
    private String caution;

    @Column(name="side_effect")
    private String sideEffect;

    @Column(name="efficacy")
    private String efficacy;

    @Column(name="unit")
    private String unit;

    @Column(name="method")
    private String method;

    @Column(name="dose_per_time")
    private Integer dosePerTime;

    @Column(name="dose_per_day")
    private Integer dosePerDay;

    @Enumerated(EnumType.STRING) // EnumType.STRING을 사용하면 문자열로 저장
    @Column(name="copayment_rate_cd")
    private CopaymentRateCd copaymentRateCd;

    @Enumerated(EnumType.STRING) // EnumType.STRING을 사용하면 문자열로 저장
    @Column(name = "time")
    private Time time;

    @Column(name="image_url")
    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDateTime createYmd;

    /* Relation */
    @OneToMany(mappedBy = "medInkMedicine", fetch = FetchType.LAZY)
    List<MedicineIntake> medicineIntakeList;

    @Builder
    public Medicine(CopaymentRateCd copaymentRateCd, String method, Integer dosePerDay, Integer dosePerTime, String medicineNm, Long medicineCd, Long price, String caution, String sideEffect, String efficacy, String unit, Time time, String imageUrl) {
        this.medicineNm = medicineNm;
        this.medicineCd = medicineCd;
        this.price = price;
        this.caution = caution;
        this.sideEffect = sideEffect;
        this.efficacy = efficacy;
        this.unit = unit;
        this.time = time;
        this.imageUrl = imageUrl;
        this.method = method;
        this.dosePerDay = dosePerDay;
        this.dosePerTime = dosePerTime;
        this.copaymentRateCd = copaymentRateCd;
        this.createYmd = LocalDateTime.now();
    }
}
