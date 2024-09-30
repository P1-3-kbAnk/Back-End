package com.kbank.backend.domain;


/*
제목 : 처방전 복약 매핑 테이블 정의
설명 : ~~~~
담당자 : 김성헌
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
@Table(name="prescription_medicine_tb")
public class PrescriptionMedicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pre_med_pk")
    private long preMedPk;

    @Column(name="total_days")
    private int totalDays;

    @Column(name = "create_ymd", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pre_med_prescription_fk") // prescription_pk를 참조
    private Prescription preMedPrescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pre_med_medicine_fk")
    private Medicine preMedMedicine;

    @Builder
    public PrescriptionMedicine(int totalDays, Prescription preMedPrescription, Medicine preMedMedicine) {
        this.totalDays = totalDays;
        this.preMedPrescription = preMedPrescription;
        this.preMedMedicine = preMedMedicine;
        this.createYmd = LocalDate.now();
    }
}
