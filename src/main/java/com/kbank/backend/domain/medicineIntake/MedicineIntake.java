package com.kbank.backend.domain.medicineIntake;

/*
제목 : 복약 여부 엔티티 정의
설명 : 복약 했는지 여부의 정보를 담은 엔티티.
      ~~~~
담당자 : 김도은
*/

import com.kbank.backend.domain.user.User;
import com.kbank.backend.enumerate.Meal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="medicine_intake_tb")
public class MedicineIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="med_ink_pk")
    private long medInkPk;

    @ManyToOne
    @JoinColumn(name="med_ink_user_fk")
    private User medInkUserFk;

    @Column(name="med_ink_medicine_fk")
    private long medInkMedicineFk;

    @Column(name="meal")
    private Meal meal;

    @Column(name="eat_st")
    private int eatSt;

}
