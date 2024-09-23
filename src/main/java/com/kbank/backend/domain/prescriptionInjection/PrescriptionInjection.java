package com.kbank.backend.domain.prescriptionInjection;


/*
제목 : 주사제 처방전 매핑 엔티티 정의
설명 : ~~~~
담당자 : 문환희
*/

import com.kbank.backend.domain.injection.Injection;
import com.kbank.backend.domain.prescription.Prescription;
import com.kbank.backend.enumerate.Rate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="prescription_injection_tb")
public class PrescriptionInjection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pre_inj_pk")
    private long preInjPk;

    @ManyToOne
    @JoinColumn(name="pre_inj_prescription_fk")
    private Prescription preInjPrescriptionFk;

    @ManyToOne
    @JoinColumn(name="pre_inj_injection_fk")
    private Injection preInjInjection;

    @Column(name="usage")
    private String usage;

    @Column(name="dose_per_time")
    private int dosePerTime;

    @Column(name="dose_per_day")
    private int dosePerDay;

    @Column(name="total_days")
    private int totalDays;

    @Column(name="copayment_rate_cd")
    private Rate rate;

}