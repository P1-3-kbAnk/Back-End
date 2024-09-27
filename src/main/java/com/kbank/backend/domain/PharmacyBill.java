package com.kbank.backend.domain;


/*
제목 : 약국 영수증 테이블 엔티티 정의
설명 : 처방전에 대한 영수증 처리 총 가격, 일시, 약국명, 약국사업자번호로 구성 됨
담당자 : 김도은
*/

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name="pharmacy_bill_tb")
public class PharmacyBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pharmacy_bill_pk")
    @Getter
    private long billPk;

    @OneToOne(fetch = FetchType.LAZY)
    @Getter
    @JoinColumn(name="pharmacy_bill_prescription_fk")
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
    @JoinColumn(name="pharmacy_bill_pharmacy_fk")
    private Pharmacy pharmacyBillPharmacyFk;

    @Builder
    public PharmacyBill(Prescription billPrescriptionFk, long totalPrice, LocalDateTime billYmd, Pharmacy pharmacyBillPharmacyFk) {
        this.billPrescriptionFk = billPrescriptionFk;
        this.totalPrice = totalPrice;
        this.billYmd = billYmd;
        this.pharmacyBillPharmacyFk = pharmacyBillPharmacyFk;
    }


    public PharmacyBill() {

    }
}
