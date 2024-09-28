package com.kbank.backend.domain;

/*
제목 : 병원 테이블 엔티티 정의
설명 : 병원 정보를 담은 엔티티.
      소속 위치, 병원 이름, 전화번호, 요양기관번호, 팩스번호로 구성 됨..
담당자 : 김도은
*/


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.address.Dong;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="hospital_tb")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hospital_pk")
    private long hospitalPk;

    @Column(name="hospital_nm", nullable=false)
    private String hospitalNm;

    @Column(name="phone_no", nullable=false)
    private String phoneNo;

    @Column(name = "hospital_no", nullable=false)
    private long hospitalNo;

    @Column(name="fax_no", nullable=false)
    private String faxNo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDate createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hospital_dong_fk")
    private Dong hospitalDong;

    public Hospital(String hospitalNm, String phoneNo, long hospitalNo, String faxNo, Dong hospitalDong) {
        this.hospitalNm = hospitalNm;
        this.phoneNo = phoneNo;
        this.hospitalNo = hospitalNo;
        this.faxNo = faxNo;
        this.hospitalDong = hospitalDong;
    }
}
