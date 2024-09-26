package com.kbank.backend.domain;

/*
제목 : 복약 여부 엔티티 정의
설명 : 복약 했는지 여부의 정보를 담은 엔티티.
      ~~~~
담당자 : 김도은
*/

import com.kbank.backend.domain.Medicine;
import com.kbank.backend.domain.User;
import com.kbank.backend.enumerate.Meal;
import lombok.*;

import jakarta.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="medicine_intake_tb")
public class MedicineIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="med_ink_pk")
    private long medInkPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="med_ink_user_fk")
    private User medInkUserFk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="med_ink_medicine_fk")
    private Medicine medInkMedicineFk;

    @Enumerated(EnumType.STRING)
    @Column(name="meal", nullable=false)
    private Meal meal;

    @Column(name="eat_st", nullable=false)
    private boolean eatSt;

}
