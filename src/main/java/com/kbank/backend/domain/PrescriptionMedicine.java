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
import java.time.LocalDateTime;

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

    @Column(name ="medicine_nm")
    private String medicineNm;

    @Column(name="dose_pre_morning")
    private Integer dosePreMorning;

    @Column(name="dose_pre_lunch")
    private Integer dosePreLunch;

    @Column(name="dose_pre_dinner")
    private Integer dosePreDinner;

    @Column(name="method")
    private String method;

    @Column(name="total_days")
    private Integer totalDays;

    @Column(name = "create_ymd", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pre_med_prescription_fk") // prescription_pk를 참조
    private Prescription preMedPrescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pre_med_medicine_fk")
    private Medicine preMedMedicine;

    @Builder
    public PrescriptionMedicine(Integer totalDays,String medicineNm, Integer dosePreMorning,Integer dosePreLunch,Integer dosePreDinner, String method, Prescription preMedPrescription, Medicine preMedMedicine) {
        this.totalDays = totalDays;
        this.preMedPrescription = preMedPrescription;
        this.preMedMedicine = preMedMedicine;
        this.medicineNm=medicineNm;
        this.dosePreMorning= dosePreMorning;
        this.dosePreLunch=dosePreLunch;
        this.dosePreDinner=dosePreDinner;
        this.method=method;
        this.createYmd = LocalDateTime.now();
    }

}
