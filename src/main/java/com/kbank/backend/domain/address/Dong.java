package com.kbank.backend.domain.address;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

/*
제목 : 주소 동 테이블 엔티티 정의
설명 : 주소 시, 구, 동 중 동을 정의한 엔티티.
      소속 구, 동 이름으로 구성 됨.
담당자 : 문환희
*/


@Entity
@Getter
@DynamicUpdate
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="dong_tb")
public class Dong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dong_pk")
    private Long dong_pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dong_gu_fk")
    private Gu dong_gu_fk;

    @Column(name="dong_nm", unique = true)
    private String dong_nm;

    @Builder
    public Dong(Gu dong_gu_fk, String dong_nm) {
        this.dong_gu_fk = dong_gu_fk;
        this.dong_nm = dong_nm;
    }

}

