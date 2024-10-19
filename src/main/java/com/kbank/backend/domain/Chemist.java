package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Tp;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chemist_tb")
public class Chemist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chemist_pk", nullable = false, updatable = false, unique = true)
    private Long chemistPk;

    @Column(name = "chemist_nm", nullable = false)
    private String chemistNm;

    @Column(name = "chemist_no", nullable = false)
    private String chemistNo;

    @Column(name = "phone_no", nullable = false)
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "chemist_tp", nullable = false)
    private Tp tp;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd", nullable = false)
    private LocalDateTime createYmd;

    /* Relation */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_user_fk")
    private AuthUser authUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chemist_pharmacy_fk")
    private Pharmacy chemistPharmacy;

    @Builder
    public Chemist(AuthUser authUser, String chemistNm, String chemistNo, String phoneNo, Gender gender, Pharmacy chemistPharmacy) {
        this.authUser = authUser;
        this.chemistNm = chemistNm;
        this.chemistNo = chemistNo;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.chemistPharmacy = chemistPharmacy;
        this.createYmd = LocalDateTime.now();
        this.tp = Tp.CHEMIST;
    }

}
