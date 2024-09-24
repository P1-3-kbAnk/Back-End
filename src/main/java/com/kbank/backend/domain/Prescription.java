package com.kbank.backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/*
제목 : 주사제 복약 여부 엔티티 정의
설명 : 주사제를 복약 했는지 여부의 정보를 담은 엔티티.
      사용자, 주사제 정보, 복약 일시, 여부로 구성 됨.
담당자 : 문환희
*/

@Entity
@Table(name="prescription_tb")
@Getter
@Setter
@RequiredArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_pk")
    private long prescriptionPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_doctor_fk")
    private Doctor preDoctorFk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_user_fk")
    private User preUserFk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_chemist_fk")
    private Chemist preChemistFk;

    @Column(name = "create_ymd")
    private LocalDateTime createYmd;

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
}