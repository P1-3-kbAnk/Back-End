package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "prescription_injection_tb")
public class PrescriptionInjection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pre_inj_pk", nullable = false, updatable = false, unique = true)
    private Long preInjPk;

    @Column(name = "total_days", nullable = false)
    private Integer totalDay;

    @Column(name = "create_ymd", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createYmd;

    @Column(name = "dose_per_morning", nullable = false)
    private Integer dosePerMorning;

    @Column(name = "dose_per_lunch", nullable = false)
    private Integer dosePerLunch;

    @Column(name = "dose_per_dinner", nullable = false)
    private Integer dosePerDinner;

    @Column(name = "method", nullable = false)
    private String method;

    @Column(name = "injection_nm", nullable = false)
    private String injectionNm;

    @Column(name = "day_cnt", nullable = false)
    private Integer dayCnt;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_inj_prescription_fk")
    private Prescription preInjPrescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_inj_injection_fk")
    private Injection preInjInjection;

    @Builder
    public PrescriptionInjection(Integer totalDay, Integer dosePerMorning, Integer dosePerLunch, Integer dosePerDinner, String method, String injectionNm, Prescription preInjPrescription, Injection preInjInjection, Integer dayCnt) {
        this.totalDay = totalDay;
        this.dosePerMorning = dosePerMorning;
        this.dosePerLunch = dosePerLunch;
        this.dosePerDinner = dosePerDinner;
        this.method = method;
        this.injectionNm = injectionNm;
        this.preInjPrescription = preInjPrescription;
        this.preInjInjection = preInjInjection;
        this.createYmd = LocalDateTime.now();
        this.dayCnt = dayCnt;
    }
}