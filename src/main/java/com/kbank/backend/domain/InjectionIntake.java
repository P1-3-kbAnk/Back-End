package com.kbank.backend.domain;


/*
제목 : 주사제 복약 여부 엔티티 정의
설명 : 주사제를 복약 했는지 여부의 정보를 담은 엔티티.
      사용자, 주사제 정보, 복약 일시, 여부로 구성 됨.
담당자 : 문환희
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
    private int eatSt;

    @Column(name = "day", nullable = false)
    private LocalDate day;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDate createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="inj_ink_user_fk")
    private User injInkUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="inj_ink_injection_fk")
    private Injection injInkInjection;

    @Builder
    public InjectionIntake(Meal meal, int eatSt, User injInkUser, Injection injInkInjection, LocalDate day) {
        this.meal = meal;
        this.eatSt = eatSt;
        this.day = day;
        this.injInkUser = injInkUser;
        this.injInkInjection = injInkInjection;
        this.createYmd = LocalDate.now();
    }
}
