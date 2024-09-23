package com.kbank.backend.domain.prescriptionMedicine;


/*
제목 : 처방전 복약 매핑 테이블 정의
설명 : ~~~~
담당자 : 김성헌
*/

import com.kbank.backend.domain.medicine.Medicine;
import com.kbank.backend.domain.prescription.Prescription;
import com.kbank.backend.enumerate.CopaymentRateCd;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name="prescription_medicine_tb")
public class PrescriptionMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pre_med_pk")
    private long preMedPk;

    //추후에 fk 참조 필요
    // ManyToOne: Prescription을 참조하는 외래 키

    @ManyToOne
    @JoinColumn(name="pre_med_prescription_fk") // prescription_pk를 참조
    private Prescription preMedPrescriptionFk;

    ////추후에 fk 참조 필요
    @ManyToOne
    @JoinColumn(name="pre_med_medicine_fk")
    private Medicine preMedMedicineFk;

    @Column(name="usage")
    private String usage;

    @Column(name="dose_per_time")
    private int dosePerTime;

    @Column(name="dose_per_day")
    private int dosePerDay;

    @Column(name="total_days")
    private int totalDays;

    @Enumerated(EnumType.STRING) // EnumType.STRING을 사용하면 문자열로 저장
    private CopaymentRateCd copaymentRateCd;


}
