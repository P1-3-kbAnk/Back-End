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
@Table(name="prescription_tb")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_pk")
    private Long prescriptionPk;

    @Column(name = "prescription_no")
    private Integer prescriptionNo;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "max_date")
    private Integer maxDate;

    @Column(name = "description")
    private String description;

    @Column(name = "prescription_st")
    private Boolean prescriptionSt;

    @Column(name = "insurance_st")
    private Boolean insuranceSt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDateTime createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_doctor_fk")
    private Doctor preDoctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_user_fk")
    private User preUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_chemist_fk")
    private Chemist preChemist;

    @Builder
    public Prescription(Integer prescriptionNo, Integer duration, String description, Doctor preDoctor, User preUser, Chemist preChemist, Integer maxDate) {
        this.prescriptionNo = prescriptionNo;
        this.duration = duration;
        this.description = description;
        this.preDoctor = preDoctor;
        this.preUser = preUser;
        this.preChemist = preChemist;
        this.createYmd = LocalDateTime.now();
        this.prescriptionSt = Boolean.FALSE;
        this.insuranceSt = Boolean.FALSE;
        this.maxDate = maxDate;
    }

    public void updatePrescriptionMaxDate(Integer maxDate) {
        this.maxDate = maxDate;
    }

    public void updatePrescriptionSt(Chemist chemist) {
        this.preChemist = chemist;
        this.prescriptionSt = !this.prescriptionSt;
    }

    public void updateInsuranceSt() {
        this.insuranceSt = !this.insuranceSt;
    }
}
