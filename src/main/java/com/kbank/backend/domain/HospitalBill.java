package com.kbank.backend.domain;


/*
제목 : 병원 영수증 테이블 엔티티 정의
설명 : 처방전에 대한 영수증 처리 총 가격, 일시, 병원명, 병원사업자번호로 구성 됨
담당자 : 김도은
*/

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name="hospital_bill_tb")
public class HospitalBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bill_pk")
    @Getter
    private long billPk;

    @OneToOne(fetch = FetchType.LAZY)
    @Getter
    @JoinColumn(name="bill_prescription_fk")
    private Prescription billPrescriptionFk;

    @Setter
    @Getter
    @Column(name="total_price", nullable = false)
    private long totalPrice;

    @Getter
    @Column(name="bill_ymd",nullable = false)
    private LocalDateTime billYmd;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @JoinColumn(name="hospital_bill_hospital_fk")
    private Hospital hospitalBillHospitalFk;

    @Builder
    public HospitalBill(Prescription billPrescriptionFk, long totalPrice, LocalDateTime billYmd, Hospital hospitalBillHospitalFk) {
        this.billPrescriptionFk = billPrescriptionFk;
        this.totalPrice = totalPrice;
        this.billYmd = billYmd;
        this.hospitalBillHospitalFk = hospitalBillHospitalFk;
    }


    public HospitalBill() {
    }
}
