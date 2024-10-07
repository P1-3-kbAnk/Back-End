package com.kbank.backend.domain;

/*
제목 : 회원 정보 엔티티 정의
설명 :
담당자 : 한상민
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Provider;
import com.kbank.backend.enumerate.Role;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_pk", nullable = false, updatable = false, unique = true)
    private Long userPk;

    @Column(name="provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(name="social_id", nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    /* User Info */
    @Column(name="user_nm")
    private String userNm;

    @Column(name="phone_no")
    private String phoneNo;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="first_no")
    private String firstNo;

    @Column(name="last_no")
    private String lastNo;

    @Column(name = "bank_nm")
    private String bankNm;

    @Column(name = "account")
    private long account;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "account_pw")
    private String accountPw;

    @Column(name = "morning_alarm")
    private LocalTime morningAlarm;

    @Column(name = "lunch_alarm")
    private LocalTime lunchAlarm;

    @Column(name = "dinner_alarm")
    private LocalTime dinnerAlarm;

    @Column(name = "create_ymd", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createYmd;

    /* Relation */
    @OneToMany(mappedBy = "medInkUser", fetch = FetchType.LAZY)
    private List<MedicineIntake> medicineIntakeList;

//    @Builder
//    public User(Provider provider, String socialId, Role role, LocalDate createYmd) {
//        this.provider = provider;
//        this.socialId = socialId;
//        this.role = role;
//        this.createYmd = createYmd;
//    }

    // 시큐리티 후 수정
    @Builder
    public User(String userNm, String phoneNo, Gender gender, String firstNo, String lastNo, String bankNm, String accountNo, String accountPw) {
        this.userNm = userNm;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.firstNo = firstNo;
        this.lastNo = lastNo;
        this.bankNm = bankNm;
        this.account = 1000000000;
        this.accountNo = accountNo;
        this.accountPw = accountPw;
        this.role = Role.USER;
        this.socialId = "none";
        this.provider = Provider.KAKAO;
        this.morningAlarm = LocalTime.of(9,0);
        this.lunchAlarm = LocalTime.of(12,0);
        this.dinnerAlarm = LocalTime.of(18,0);
        this.createYmd = LocalDateTime.now();
    }

    /* Update */

    //계좌 잔액 변경
    public void setAccount(long newAccount) {
        this.account = newAccount;
    }

}
