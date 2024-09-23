package com.kbank.backend.domain.address.gu;





/*
제목 : 주소 구 테이블 엔티티 정의
설명 : 시, 구, 동 중 구 정보를 담은 엔티티.
      소속 시, 구 이름으로 구성 됨.
담당자 : 문환희
*/

import com.kbank.backend.domain.address.si.Si;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Builder
@Data
@NoArgsConstructor
@Table(name="gu_tb")
public class Gu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gu_pk")
    private long guPk;

    @ManyToOne
    @JoinColumn(name="gu_si_fk")
    private Si guSiFk;

    @Column(name="gu_nm")
    private String guNm;

    public Gu(Long pk ,Si guSiFk, String guNm) {
        this.guSiFk = guSiFk;
        this.guNm = guNm;
    }

}