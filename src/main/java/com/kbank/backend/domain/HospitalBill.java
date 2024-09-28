package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="hospital_bill_tb")
public class HospitalBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hospital_bill_pk")
    private long hospitalBillPk;

    @Column(name="total_price", nullable = false)
    private long totalPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDate createYmd;

    /* Relation */
    @ManyToOne
    @JoinColumn(name="hospital_bill_prescription_fk", nullable = false)
    private Prescription hospitalBillPrescription;

    @ManyToOne
    @JoinColumn(name="hospital_bill_hospital_fk", nullable = false)
    private Hospital hospitalBillHospital;

    @Builder
    public HospitalBill(long totalPrice, Prescription hospitalBillPrescription, Hospital hospitalBillHospital) {
        this.totalPrice = totalPrice;
        this.hospitalBillPrescription = hospitalBillPrescription;
        this.hospitalBillHospital = hospitalBillHospital;
    }
}
