package com.kbank.backend.domain;

/*
제목 : 의사 테이블 엔티티 정의
설명 : 의사 회원의 정보를 담은 엔티티.
      소속 병원, 이름, 면허종별, 면허번호, 전화번호, 성별, 주민번호, 소셜아이디 제공자, 소셜아이디, 유저 권한, 생성 일자로 구성 됨.
담당자 : 한상민
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Provider;
import com.kbank.backend.enumerate.Role;
import com.kbank.backend.enumerate.Tp;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="doctor_tb")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doctor_pk")
    private long doctorPk;

    @Column(name="doctor_nm")
    private String doctorNm;

    @Enumerated(EnumType.STRING)
    @Column(name="doctor_tp")
    private Tp tp;

    @Column(name="doctor_no")
    private String doctorNo;

    @Column(name="phone_no")
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private Gender gender;

    // 없애야됨
//    @Column(name="first_no")
//    private String firstNo;
//
//    @Column(name="last_no")
//    private String lastNo;

    @Enumerated(EnumType.STRING)
    @Column(name="provider")
    private Provider provider;

    @Column(name="social_id")
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDate createYmd;

    /* Relation */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doctor_hospital_fk")
    private Hospital doctorHospital;

    @Builder
    public Doctor(String doctorNm, Tp tp, String doctorNo, String phoneNo, Gender gender, Hospital doctorHospital) {
        this.doctorNm = doctorNm;
        this.tp = tp;
        this.doctorNo = doctorNo;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.role = Role.USER;
        this.socialId = "none";
        this.provider = Provider.KAKAO;
        this.doctorHospital = doctorHospital;
    }
}
