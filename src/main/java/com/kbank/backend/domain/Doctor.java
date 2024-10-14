package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Tp;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="doctor_tb")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_pk")
    private Long doctorPk;

    @Column(name = "doctor_nm")
    private String doctorNm;

    @Enumerated(EnumType.STRING)
    @Column(name = "doctor_tp")
    private Tp tp;

    @Column(name = "doctor_no")
    private String doctorNo;

    @Column(name = "phone_no")
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDateTime createYmd;

    /* Relation */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_user_fk")
    private AuthUser authUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_hospital_fk")
    private Hospital doctorHospital;

    @Builder
    public Doctor(String doctorNm, Tp tp, String doctorNo, String phoneNo, Gender gender, Hospital doctorHospital, AuthUser authUser) {
        this.authUser = authUser;
        this.doctorNm = doctorNm;
        this.tp = tp;
        this.doctorNo = doctorNo;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.doctorHospital = doctorHospital;
        this.createYmd = LocalDateTime.now();
    }
}
