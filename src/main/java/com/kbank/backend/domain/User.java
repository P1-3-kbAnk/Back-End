package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.enumerate.Gender;
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
    @Column(name = "user_pk", nullable = false, updatable = false, unique = true)
    private Long userPk;

    @Column(name = "user_nm")
    private String userNm;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "first_no")
    private String firstNo;

    @Column(name = "last_no")
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

    @Column(name = "fcm_no")
    private String fcmNo;

    /* Relation */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_user_fk")
    private AuthUser authUser;

    @OneToMany(mappedBy = "medInkUser", fetch = FetchType.LAZY)
    private List<MedicineIntake> medicineIntakeList;

    @Builder
    public User(AuthUser authUser, String userNm, String phoneNo, Gender gender, String firstNo, String lastNo, String bankNm, String accountNo, String accountPw) {
        this.authUser = authUser;
        this.userNm = userNm;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.firstNo = firstNo;
        this.lastNo = lastNo;
        this.bankNm = bankNm;
        this.account = 1000000000;
        this.accountNo = accountNo;
        this.accountPw = accountPw;
        this.morningAlarm = LocalTime.of(9,0);
        this.lunchAlarm = LocalTime.of(12,0);
        this.dinnerAlarm = LocalTime.of(18,0);
        this.createYmd = LocalDateTime.now();
    }

    /* Update */

    //계좌 잔액 변경
    public void updateAccount(long newAccount) {
        this.account = newAccount;
    }

    /**계좌정보수정**/
    public void updateBankNm(String bankNm){
        this.bankNm=bankNm;
    }

    public void updateAccountNo(String accountNo){
        this.accountNo=accountNo;
    }

    /**알림시간변경**/
    public void updateAlarm(UserRequestDto userRequestDto){
        this.morningAlarm=userRequestDto.getMorningAlarm();
        this.lunchAlarm=userRequestDto.getLunchAlarm();
        this.dinnerAlarm=userRequestDto.getDinnerAlarm();
    }

    public void updateFcmNo(String fcmNo) {
        this.fcmNo = fcmNo;
    }
}
