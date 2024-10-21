package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pharmacy_bill_tb")
public class PharmacyBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pharmacy_bill_pk", nullable = false, updatable = false, unique = true)
    private Long pharmacyBillPk;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd", nullable = false)
    private LocalDateTime createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_bill_prescription_fk", nullable = false)
    private Prescription pharmacyBillPrescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_bill_pharmacy_fk", nullable = false)
    private Pharmacy pharmacyBillPharmacy;

    @Builder
    public PharmacyBill(Long totalPrice, Prescription pharmacyBillPrescription, Pharmacy pharmacyBillPharmacy) {
        this.totalPrice = totalPrice;
        this.pharmacyBillPrescription = pharmacyBillPrescription;
        this.pharmacyBillPharmacy = pharmacyBillPharmacy;
        this.createYmd = LocalDateTime.now();
    }
}
