package com.kbank.backend.domain;

import lombok.Builder;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "report_tb")
public class Report {

    /* Field */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_pk", nullable = false, updatable = false, unique = true)
    private Long reportPk;

    @Column(name = "intake_method", nullable = false)
    private String intakeMethod;

    @Column(name = "exercise", nullable = false)
    private String exercise;

    @Column(name = "food", nullable = false)
    private String food;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd", nullable = false)
    private LocalDateTime createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_prescription_fk", nullable = false)
    private Prescription reportPrescription;

    @Builder
    public Report(String intakeMethod, String exercise, String food, Prescription reportPrescription) {
        this.intakeMethod = intakeMethod;
        this.exercise = exercise;
        this.food = food;
        this.reportPrescription = reportPrescription;
        this.createYmd = LocalDateTime.now();
    }
}