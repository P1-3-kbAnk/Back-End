package com.kbank.backend.domain.address;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "dong_tb")
public class Dong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dong_pk", nullable = false, updatable = false, unique = true)
    private Long dong_pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dong_gu_fk")
    private Gu dong_gu_fk;

    @Column(name = "dong_nm", nullable = false)
    private String dong_nm;

    @Builder
    public Dong(Gu dong_gu_fk, String dong_nm) {
        this.dong_gu_fk = dong_gu_fk;
        this.dong_nm = dong_nm;
    }

}

