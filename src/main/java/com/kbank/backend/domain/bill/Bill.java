package com.kbank.backend.domain.bill;


/*
제목 : 병원 영수증 테이블 엔티티 정의
설명 : 처방전에 대한 영수증 처리 총 가격, 일시로 구성 됨
담당자 : 김도은
*/

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="bill_tb")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bill_pk")
    private long billPk;

    @Column(name="bill_prescription_fk")
    private long billPrescriptionFk;

    @Column(name="total_price")
    private long totalPrice;

    //DATE 타입 LocalDate로 처리하는게 맞나?
    @Column(name="bill_ymd")
    private LocalDateTime billYmd;
}
