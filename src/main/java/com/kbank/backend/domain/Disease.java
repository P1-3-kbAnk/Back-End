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

    @Builder
    public Disease(String diseaseCd) {
        this.diseaseCd = diseaseCd;
    }
}