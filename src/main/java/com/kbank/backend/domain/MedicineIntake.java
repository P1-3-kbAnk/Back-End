package com.kbank.backend.domain;

/*
제목 : 복약 여부 엔티티 정의
설명 : 복약 했는지 여부의 정보를 담은 엔티티.
      ~~~~
담당자 : 김도은
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.enumerate.Meal;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="medicine_intake_tb")
public class MedicineIntake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="med_ink_pk")
    private long medInkPk;

    @Enumerated(EnumType.STRING)
    @Column(name="meal", nullable=false)
    private Meal meal;

    @Column(name = "day", nullable = false)
    private LocalDate day;

    @Column(name="eat_st", nullable=false)
    private boolean eatSt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDate createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="med_ink_user_fk")
    private User medInkUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="med_ink_medicine_fk")
    private Medicine medInkMedicine;

    @Builder
    public MedicineIntake(Meal meal, boolean eatSt, User user, Medicine medicine, LocalDate day) {
        this.meal = meal;
        this.day = day;
        this.eatSt = eatSt;
        this.medInkUser = user;
        this.medInkMedicine = medicine;
    }

    /* Update */
    public Boolean updateEatSt() {
        this.eatSt = !this.eatSt;

        return Boolean.TRUE;
    }
}
