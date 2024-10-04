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
@Table(name="injection_intake_tb")
public class InjectionIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inj_ink_pk")
    private long injInkPk;

    @Enumerated(EnumType.STRING)
    @Column(name="meal")
    private Meal meal;

    @Column(name="eat_st")
    private Boolean eatSt;

    @Column(name = "day", nullable = false)
    private LocalDate day;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDateTime createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="inj_ink_user_fk")
    private User injInkUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="inj_ink_injection_fk")
    private Injection injInkInjection;

    @Builder
    public InjectionIntake(Meal meal, Boolean eatSt, User injInkUser, Injection injInkInjection, LocalDate day) {
        this.meal = meal;
        this.eatSt = eatSt;
        this.day = day;
        this.injInkUser = injInkUser;
        this.injInkInjection = injInkInjection;
        this.createYmd = LocalDateTime.now();
    }

    /* update */

    public Boolean updateEatSt() {
        this.eatSt = !this.eatSt;
        return Boolean.TRUE;
    }
}
