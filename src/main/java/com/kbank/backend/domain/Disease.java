package com.kbank.backend.domain;


/*
제목 : 질병코드 테이블 엔티티 정의
설명 : 처방전에 담긴 질병들에 대한 정보를 담음.
      처방전, 질병 코드로 구성 됨.
담당자 : 김도은
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="disease_tb")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="disease_pk")
    private long diseasePk;

    @Column(name="disease_cd", nullable = false)
    private String diseaseCd;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDateTime createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="disease_prescription_fk")
    private Prescription diseasePrescription;

    @Builder
    public Disease(String diseaseCd, Prescription diseasePrescription) {
        this.diseaseCd = diseaseCd;
        this.diseasePrescription = diseasePrescription;
        this.createYmd = LocalDateTime.now();
    }
}