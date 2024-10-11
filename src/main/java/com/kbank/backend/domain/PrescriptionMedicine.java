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
@Table(name="prescription_medicine_tb")
public class PrescriptionMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pre_med_pk")
    private Long preMedPk;

    @Column(name ="medicine_nm")
    private String medicineNm;

    @Column(name="dose_per_morning")
    private Integer dosePerMorning;

    @Column(name="dose_per_lunch")
    private Integer dosePerLunch;

    @Column(name="dose_pre_dinner")
    private Integer dosePerDinner;

    @Column(name="method")
    private String method;

    @Column(name="total_day")
    private Integer totalDay;

    @Column(name="day_cnt")
    private Integer dayCnt;

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
    public PrescriptionMedicine(String medicineNm, Integer dosePerMorning, Integer dosePerLunch, Integer dosePerDinner, String method, Integer totalDay, Prescription preMedPrescription, Medicine preMedMedicine,Integer dayCnt) {
        this.medicineNm = medicineNm;
        this.dosePerMorning = dosePerMorning;
        this.dosePerLunch = dosePerLunch;
        this.dosePerDinner = dosePerDinner;
        this.method = method;
        this.totalDay = totalDay;
        this.preMedPrescription = preMedPrescription;
        this.preMedMedicine = preMedMedicine;
        this.createYmd = LocalDateTime.now();
        this.dayCnt=dayCnt;
    }

}
