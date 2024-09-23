package com.kbank.backend.domain.pharmacy;


/*
제목 : 약국 정보 테이블 엔티티 정의
설명 : ~~~~
담당자 : 최규찬
*/

import com.kbank.backend.domain.address.dong.Dong;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="pharmacy_tb")
@Getter
@Setter
@RequiredArgsConstructor
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto_increment 설정
    @Column(name = "pharmacy_pk")
    private Long pharmacyPk;

    @ManyToOne
    @JoinColumn(name = "pharmacy_dong_fk", nullable = false)
    private Dong pharmacyDongFk;  // 동 필드에 대한 FK

    @Column(name = "pharmacy_nm", nullable = false)
    private String pharmacyNm;  // 약국 이름

    @Column(name = "phone_no", nullable = false)
    private String phoneNo;  // 전화번호

    @Column(name = "fax_no")
    private String faxNo;  // 팩스번호
}
