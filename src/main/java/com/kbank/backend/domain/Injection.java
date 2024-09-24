package com.kbank.backend.domain;


/*
제목 : 주사제 정보 테이블 엔티티 정의
설명 : 주사제 정보를 담은 엔티티.
      주사제 이름, 주사제 코드, 가격, 유의사항, 용량 단위, 효능, 원내조제 및 원외처방 여부로 구성 됨.
담당자 : 임준수
*/

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="injection_tb")
public class Injection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="injection_pk")
    private long injectionPk;

    @Column(name="injection_nm")
    private String injectionNm;

    @Column(name="injection_cd")
    private long injectionCd;

    @Column(name="efficacy")
    private String efficacy;

    @Column(name="method")
    private int method;

}
