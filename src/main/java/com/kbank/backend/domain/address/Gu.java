package com.kbank.backend.domain.address;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "gu_tb")
public class Gu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gu_pk", nullable = false, updatable = false, unique = true)
    private Long gu_pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gu_si_fk")
    private Si gu_si_fk;

    @Column(name = "gu_nm", unique = true)
    private String gu_nm;

    @Builder
    public Gu(Si gu_si_fk, String gu_nm) {
        this.gu_si_fk = gu_si_fk;
        this.gu_nm = gu_nm;
    }

}