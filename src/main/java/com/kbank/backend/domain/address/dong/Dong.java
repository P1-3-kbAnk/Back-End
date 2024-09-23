package com.kbank.backend.domain.address.dong;

import com.kbank.backend.domain.address.gu.Gu;
import jakarta.persistence.*;

/*
제목 : 주소 동 테이블 엔티티 정의
설명 : 주소 시, 구, 동 중 동을 정의한 엔티티.
      소속 구, 동 이름으로 구성 됨.
담당자 : 문환희
*/


@Entity
@Table(name="dong_tb")
public class Dong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dong_pk")
    private long dongPk;

    @ManyToOne
    @JoinColumn(name="dong_gu_fk")
    private Gu dongGuFk;

    @Column(name="dong_nm")
    private String dongNm;

}

