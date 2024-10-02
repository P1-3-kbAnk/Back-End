package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="prescription_disease_tb")
public class PrescriptionDisease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pre_dis_pk")
    private Long preDisPk;

    @Column(name = "create_ymd", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pre_dis_prescription_fk")
    private Prescription preDisPrescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pre_dis_disease_fk")
    private Disease preDisDisease;

    @Builder
    public PrescriptionDisease(Prescription preDisPrescription, Disease preDisDisease) {
        this.preDisPrescription = preDisPrescription;
        this.preDisDisease = preDisDisease;
        this.createYmd = LocalDateTime.now();
    }
}