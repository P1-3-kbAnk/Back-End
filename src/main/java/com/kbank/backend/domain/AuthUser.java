package com.kbank.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.enumerate.Provider;
import com.kbank.backend.enumerate.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="auth_user_tb")
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="auth_user_pk", nullable = false, updatable = false, unique = true)
    private Long authUserPk;

    @Column(name="provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(name="social_id", nullable = false)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    @Column(name = "create_ymd", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createYmd;

    @Builder
    public AuthUser(Provider provider, String socialId, Role role) {
        this.provider = provider;
        this.socialId = socialId;
        this.role = role;
        this.createYmd = LocalDateTime.now();
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
