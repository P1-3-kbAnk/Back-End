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
import java.time.LocalDateTime;

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
    private Integer totalDays;

    @Column(name = "create_ymd", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createYmd;

    @Column(name="dose_per_morning")
    private Integer dosePerMorning;

    @Column(name="dose_per_lunch")
    private Integer dosePerLunch;

    @Column(name="dose_per_dinner")
    private Integer dosePerDinner;

    @Column(name="method")
    private String method;

    @Column(name ="injection_nm")
    private String injectionNm;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pre_inj_prescription_fk")
    private Prescription preInjPrescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pre_inj_injection_fk")
    private Injection preInjInjection;

    public PrescriptionInjection(Integer totalDays, Integer dosePerMorning, Integer dosePerLunch, Integer dosePerDinner, String method, String injectionNm, Prescription preInjPrescription, Injection preInjInjection) {
        this.totalDays = totalDays;
        this.dosePerMorning = dosePerMorning;
        this.dosePerLunch = dosePerLunch;
        this.dosePerDinner = dosePerDinner;
        this.method = method;
        this.injectionNm = injectionNm;
        this.preInjPrescription = preInjPrescription;
        this.preInjInjection = preInjInjection;
        this.createYmd=LocalDateTime.now();
    }
}