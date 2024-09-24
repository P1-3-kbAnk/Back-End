package com.kbank.backend.domain;


/*
제목 : 주사제 복약 여부 엔티티 정의
설명 : 주사제를 복약 했는지 여부의 정보를 담은 엔티티.
      사용자, 주사제 정보, 복약 일시, 여부로 구성 됨.
담당자 : 문환희
*/


import com.kbank.backend.enumerate.Meal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="injection_intake_tb")
public class InjectionIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inj_ink_pk")
    private long injInkPk;

    @ManyToOne
    @JoinColumn(name="inj_ink_user_fk")
    private User injInkUserFk;

    @ManyToOne
    @JoinColumn(name="inj_ink_injection_fk")
    private Injection injInkInjectionFk;

    @Column(name="meal")
    private Meal meal;

    @Column(name="eat_st")
    private int eatSt;





}
