package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;


import java.time.LocalDateTime;

/*
제목 : 주사제 복약 여부 엔티티 정의
설명 : 주사제를 복약 했는지 여부의 정보를 담은 엔티티.
      사용자, 주사제 정보, 복약 일시, 여부로 구성 됨.
담당자 : 문환희
*/

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="prescription_tb")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_pk")
    private long prescriptionPk;

    @Column(name = "prescription_no")
    private int prescriptionNo;

    @Column(name = "duration")
    private int duration;

    @Column(name = "description")
    private String description;

    @Column(name = "prescription_st")
    private boolean prescriptionSt;

    @Column(name = "insurance_st")
    private boolean insuranceSt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDateTime createYmd;

    @PrePersist
    public void prePersist() {
        this.createYmd = (this.createYmd == null) ? LocalDateTime.now() : this.createYmd;
    }

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
    public Prescription(Doctor preDoctor, User preUser, Chemist preChemist, int prescriptionNo, int duration, String description, boolean prescriptionSt, boolean insuranceSt) {
        this.preDoctor = preDoctor;
        this.preUser = preUser;
        this.preChemist = preChemist;
        this.prescriptionNo = prescriptionNo;
        this.duration = duration;
        this.description = description;
        this.prescriptionSt = prescriptionSt;
        this.insuranceSt = insuranceSt;
    }


}
