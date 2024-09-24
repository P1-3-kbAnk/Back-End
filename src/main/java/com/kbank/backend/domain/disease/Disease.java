package com.kbank.backend.domain.disease;


/*
제목 : 질병코드 테이블 엔티티 정의
설명 : 처방전에 담긴 질병들에 대한 정보를 담음.
      처방전, 질병 코드로 구성 됨.
담당자 : 김도은
*/

import com.kbank.backend.domain.prescription.Prescription;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="disease_tb")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="disease_pk")
    private long diseasePk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="disease_prescription_fk")
    private Prescription diseasePrescriptionFk;

    @Column(name="disease_cd")
    private String diseaseCd;

}