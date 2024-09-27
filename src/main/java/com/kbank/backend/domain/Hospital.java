package com.kbank.backend.domain;
/*
제목 : 병원 테이블 엔티티 정의
설명 : 병원 정보를 담은 엔티티.
      소속 위치, 병원 이름, 전화번호, 요양기관번호, 팩스번호로 구성 됨..
담당자 : 김도은
*/

import com.kbank.backend.domain.address.Dong;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name="hospital_tb")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hospital_pk")
    @Getter
    private long hospitalPk;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hospital_dong_fk")
    private Dong hospitalDongFk;

    @Getter
    @Column(name="hospital_nm", nullable=false)
    private String hospitalNm;

    @Getter
    @Column(name="phone_no", nullable=false)
    private String phoneNo;

    @Getter
    @Column(name = "hospital_no", nullable=false)
    private long hospitalNo;

    @Getter
    @Column(name="fax_no", nullable=false)
    private String faxNo;

    @Builder
    public Hospital(Dong hospitalDongFk, String hospitalNm, String phoneNo, long hospitalNo, String faxNo) {
        this.hospitalDongFk = hospitalDongFk;
        this.hospitalNm = hospitalNm;
        this.phoneNo = phoneNo;
        this.hospitalNo = hospitalNo;
        this.faxNo = faxNo;
    }

    public Hospital() {
    }
}
