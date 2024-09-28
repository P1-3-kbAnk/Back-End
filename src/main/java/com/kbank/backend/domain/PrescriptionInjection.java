package com.kbank.backend.domain;


/*
제목 : 주사제 처방전 매핑 엔티티 정의
설명 : ~~~~
담당자 : 문환희
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="prescription_injection_tb")
public class PrescriptionInjection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pre_inj_pk")
    private long preInjPk;

    @Column(name="total_days")
    private int totalDays;
//
//    @Column(name="usage")
//    private String usage;
//
//    @Column(name="dose_per_time")
//    private int dosePerTime;
//
//    @Column(name="dose_per_day")
//    private int dosePerDay;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name="copayment_rate_cd")
//    private Rate rate;

    @Column(name = "create_ymd", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pre_inj_prescription_fk")
    private Prescription preInjPrescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pre_inj_injection_fk")
    private Injection preInjInjection;

    @Builder
    public PrescriptionInjection(int totalDays, Prescription preInjPrescription, Injection preInjInjection) {
        this.totalDays = totalDays;
        this.preInjPrescription = preInjPrescription;
        this.preInjInjection = preInjInjection;
    }
}