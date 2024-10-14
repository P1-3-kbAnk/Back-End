package com.kbank.backend.enumerate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    USER("USER", "ROLE_USER"),
    DOCTOR("DOCTOR", "ROLE_USER"),
    CHEMIST("CHEMIST", "ROLE_USER"),
    ADMIN("ADMIN", "ROLE_ADMIN"),
    GUEST("GUEST", "ROLE_GUEST");

    private final String name;
    private final String securityName;

    @Override
    public String toString() {
        return this.name;
    }

    public String toSecurityString() {
        return this.securityName;
    }
}
