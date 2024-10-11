package com.kbank.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="disease_tb")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="disease_pk")
    private Long diseasePk;

    @Column(name="disease_cd", nullable = false)
    private String diseaseCd;

    @Column(name="disease_nm", nullable = false)
    private String diseaseNm;

    @Builder
    public Disease(String diseaseCd, String diseaseNm) {
        this.diseaseCd = diseaseCd;
        this.diseaseNm = diseaseNm;
    }
}