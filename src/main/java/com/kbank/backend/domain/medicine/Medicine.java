package com.kbank.backend.domain.medicine;


/*
제목 : 복약 정보 테이블 엔티티 정의
설명 : 약에 대한 정보를 담은 엔티티
담당자 : 김성헌
*/

import com.kbank.backend.enumerate.Time;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name="medicine_tb")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private Long medicine_pk;

    @Column(name="medicine_nm")
    private String medicineNm;

    @Column(name="medicine_cd")
    private Long medicineCd;

    @Column(name="price")
    private Long price;

    @Column(name="caution")
    private String caution;

    @Column(name="side_effect")
    private String sideEffect;

    @Column(name="efficacy")
    private String efficacy;

    @Column(name="unit")
    private String unit;

    @Enumerated(EnumType.STRING) // EnumType.STRING을 사용하면 문자열로 저장
    private Time time;

    @Column(name="image_url")
    private String imageUrl;


}
