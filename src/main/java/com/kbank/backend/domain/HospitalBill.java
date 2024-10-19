package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hospital_bill_tb")
public class HospitalBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_bill_pk")
    private Long hospitalBillPk;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd", nullable = false)
    private LocalDateTime createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_bill_prescription_fk", nullable = false)
    private Prescription hospitalBillPrescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_bill_hospital_fk", nullable = false)
    private Hospital hospitalBillHospital;

    @Builder
    public HospitalBill(Long totalPrice, Prescription hospitalBillPrescription, Hospital hospitalBillHospital) {
        this.totalPrice = totalPrice;
        this.hospitalBillPrescription = hospitalBillPrescription;
        this.hospitalBillHospital = hospitalBillHospital;
        this.createYmd = LocalDateTime.now();
    }

}
