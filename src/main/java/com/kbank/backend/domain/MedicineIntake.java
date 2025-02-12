package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.enumerate.Meal;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "medicine_intake_tb")
public class MedicineIntake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "med_ink_pk", nullable = false, updatable = false, unique = true)
    private Long medInkPk;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal", nullable = false)
    private Meal meal;

    @Column(name = "day", nullable = false)
    private LocalDate day;

    @Column(name = "eat_st", nullable = false)
    private Boolean eatSt;

    @Column(name = "intake_cnt", nullable = false)
    private Integer intakeCnt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDateTime createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "med_ink_user_fk")
    private User medInkUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "med_ink_medicine_fk")
    private Medicine medInkMedicine;

    @Builder
    public MedicineIntake(Meal meal, LocalDate day, Integer intakeCnt, User medInkUser, Medicine medInkMedicine) {
        this.meal = meal;
        this.day = day;
        this.intakeCnt = intakeCnt;
        this.medInkUser = medInkUser;
        this.medInkMedicine = medInkMedicine;
        this.createYmd = LocalDateTime.now();
        this.eatSt = false;
    }

    /* Update */
    public Boolean updateEatSt() {
        this.eatSt = !this.eatSt;
        return Boolean.TRUE;
    }
}
