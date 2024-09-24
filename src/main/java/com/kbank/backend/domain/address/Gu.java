package com.kbank.backend.domain.address;

/*
제목 : 주소 구 테이블 엔티티 정의
설명 : 시, 구, 동 중 구 정보를 담은 엔티티.
      소속 시, 구 이름으로 구성 됨.
담당자 : 문환희
*/

import com.kbank.backend.domain.address.Si;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="gu_tb")
public class Gu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gu_pk")
    private long gu_pk;

    @ManyToOne
    @JoinColumn(name="gu_si_fk")
    private Si gu_si_fk;

    @Column(name="gu_nm")
    private String gu_nm;
 
}