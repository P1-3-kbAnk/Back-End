package com.kbank.backend.domain;


/*
제목 : 리포트 테이블 엔티티 정의
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
@Table(name="report_tb")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="report_pk")
    private long reportPk;

    @Column(name="intake_method")
    private String intakeMethod;

    @Column(name="exercise")
    private String exercise;

    @Column(name="food")
    private String food;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDate createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="report_prescription_fk")
    private Prescription reportPrescription;

    @Builder
    public Report(String intakeMethod, String exercise, String food, Prescription reportPrescription) {
        this.intakeMethod = intakeMethod;
        this.exercise = exercise;
        this.food = food;
        this.reportPrescription = reportPrescription;
    }
}