package com.kbank.backend.domain.report;


/*
제목 : 리포트 테이블 엔티티 정의
설명 : ~~~~
담당자 : 김성헌
*/

import com.kbank.backend.domain.Prescription;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@RequiredArgsConstructor
@Entity
@Table(name="report_tb")
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="report_pk")
    private long reportPk;

    @ManyToOne
    @JoinColumn(name="report_prescription_fk")
    private Prescription reportPrescriptionFK;

    @Column(name="intake_method")
    private String intakeMethod;

    @Column(name="exercise")
    private String exercise;

    @Column(name="food")
    private String food;
}