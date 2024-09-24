package com.kbank.backend.domain.hospital;

/*
제목 : 병원 테이블 엔티티 정의
설명 : 병원 정보를 담은 엔티티.
      소속 위치, 병원 이름, 전화번호, 요양기관번호, 팩스번호로 구성 됨..
담당자 : 김도은
*/


import com.kbank.backend.domain.address.dong.Dong;
import lombok.*;

import jakarta.persistence.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="hospital_tb")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hospital_pk")
    private long hospitalPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hospital_dong_fk")
    private Dong hospitalDongFk;

    @Column(name="hospital_nm")
    private String hospitalNm;

    @Column(name="phone_no")
    private String phoneNo;

    @Column(name = "hospital_no")
    private long hospitalNo;

    @Column(name="fax_no")
    private String faxNo;

}
