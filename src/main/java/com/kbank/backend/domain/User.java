package com.kbank.backend.domain;

/*
제목 : 회원 정보 엔티티 정의
설명 :
담당자 : 한상민
*/

import com.kbank.backend.enumerate.Gender;
import com.kbank.backend.enumerate.Provider;
import com.kbank.backend.enumerate.Role;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
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

    @Column(name="role", nullable = false)
    private Role role;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createTime;

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

    /* Relation */
    // one to many 와 같은 관계 설정

    @Builder
    public User(Provider provider, String socialId, Role role, LocalDateTime createTime) {
        this.provider = provider;
        this.socialId = socialId;
        this.role = role;
        this.createTime = createTime;
    }

    /* Update */
    
    public void setRole(Role role) {
        this.role = role;
    }
}
