package com.kbank.backend.domain;

/*
제목 : 약사 테이블 엔티티 정의
설명 : 약사 회원에 대한 정보를 담은 엔티티로 구성 됨.
      소속 약국, 이름, 면허종별, 면허번호, 전화번호, 성별, 주민번호, 소셜 로그인 제공자, 소셜아이디, 유저 권한, 생성 일자로 구성.
담당자 : 한상민
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Provider;
import com.kbank.backend.enumerate.Role;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chemist_tb")
public class Chemist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto_increment 설정
    @Column(name = "chemist_pk")
    private Long chemistPk;

    @Column(name = "chemist_nm", nullable = false)
    private String chemistNm;

    // 면허 종별 구현 필
//    @Enumerated(EnumType.STRING)
//    @Column(name = "chemist_tp", nullable = false)
//    private LicenseType licenseType;

    @Column(name = "chemist_no", nullable = false)
    private String chemistNo;

    @Column(name = "phone_no")
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    //없애야됨
//    @Column(name = "first_no", nullable = false)
//    private String firstNo;  // 주민등록번호 앞자리
//
//    @Column(name = "last_no", nullable = false)
//    private String lastNo;   // 주민등록번호 뒷자리

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private Provider provider;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_ymd")
    private LocalDate createYmd;

    /* Relation */
    @ManyToOne
    @JoinColumn(name="chemist_pharmacy_fk")
    private Pharmacy chemistPharmacy;

    @Builder
    public Chemist(String chemistNm, String chemistNo, String phoneNo, Gender gender, Pharmacy chemistPharmacy) {

        this.chemistNm = chemistNm;
        this.chemistNo = chemistNo;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.role = Role.USER;
        this.socialId = "none";
        this.provider = Provider.KAKAO;
        this.chemistPharmacy = chemistPharmacy;
    }
}
