package com.kbank.backend.domain.user;


/*
제목 : 회원 정보 엔티티 정의
설명 :
담당자 : 한상민
*/

import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Provider;
import com.kbank.backend.enumerate.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name="user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    @Column(name="user_pk")
    private Long userPk;

    @Column(name="user_nm")
    private String userNm;

    @Column(name="phone_no")
    private String phoneNo;

    @Column(name="gender")
    private Gender gender;

    @Column(name="first_no")
    private int firstNo;

    @Column(name="last_no")
    private int lastNo;

    @Column(name="provider")
    private Provider provider;

    @Column(name="social_id")
    private String socialId;

    @Column(name="role")
    private Role role;


    @Column(name = "bank_nm", nullable = false)
    private String bankNm;

    @Column(name = "account", nullable = false)
    private long account;

    @Column(name = "account_no", nullable = false)
    private String accountNo;

    @Column(name = "account_pw", nullable = false)
    private String accountPw;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;


}
