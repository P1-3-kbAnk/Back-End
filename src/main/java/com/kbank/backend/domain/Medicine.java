package com.kbank.backend.domain;

import com.kbank.backend.enumerate.CopaymentRateCd;
import com.kbank.backend.enumerate.Time;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import java.util.List;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "medicine_tb")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_pk", nullable = false, updatable = false, unique = true)
    private Long medicinePk;

    @Column(name = "medicine_nm", nullable = false)
    private String medicineNm;

    @Column(name = "medicine_cd", nullable = false)
    private Long medicineCd;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "caution", nullable = false)
    private String caution;

    @Column(name = "side_effect", nullable = false)
    private String sideEffect;

    @Column(name = "efficacy", nullable = false)
    private String efficacy;

    @Column(name = "unit", nullable = false)
    private String unit; //용량 단위

    @Enumerated(EnumType.STRING)
    @Column(name = "copayment_rate_cd", nullable = false)
    private CopaymentRateCd copaymentRateCd;

    @Enumerated(EnumType.STRING)
    @Column(name = "time", nullable = false)
    private Time time;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    /* Relation */
    @OneToMany(mappedBy = "medInkMedicine", fetch = FetchType.LAZY)
    List<MedicineIntake> medicineIntakeList;

    @Builder
    public Medicine(CopaymentRateCd copaymentRateCd, String medicineNm, Long medicineCd, Long price, String caution, String sideEffect, String efficacy, String unit, Time time, String imageUrl) {
        this.medicineNm = medicineNm;
        this.medicineCd = medicineCd;
        this.price = price;
        this.caution = caution;
        this.sideEffect = sideEffect;
        this.efficacy = efficacy;
        this.unit = unit;
        this.time = time;
        this.imageUrl = imageUrl;
        this.copaymentRateCd = copaymentRateCd;
    }

}
