package com.kbank.backend.domain.address.dong;

import com.kbank.backend.domain.address.gu.Gu;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/*
제목 : 주소 동 테이블 엔티티 정의
설명 : 주소 시, 구, 동 중 동을 정의한 엔티티.
      소속 구, 동 이름으로 구성 됨.
담당자 : 문환희
*/


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="dong_tb")
public class Dong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dong_pk")
    private long dong_pk;

    @ManyToOne
    @JoinColumn(name="dong_gu_fk")
    private Gu dong_gu_fk;

    @Column(name="dong_nm")
    private String dong_nm;

}

