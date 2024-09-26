package com.kbank.backend.domain;


/*
제목 : 병원 영수증 테이블 엔티티 정의
설명 : 처방전에 대한 영수증 처리 총 가격, 일시로 구성 됨
담당자 : 김도은
*/


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="bill_tb")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bill_pk")
    private long billPk;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bill_prescription_fk")
    private Prescription billPrescriptionFk;

    @Column(name="total_price", nullable = false)
    private long totalPrice;

    @Column(name="bill_ymd",nullable = false)
    private LocalDateTime billYmd;
}
