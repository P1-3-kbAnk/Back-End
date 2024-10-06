package com.kbank.backend.security.info;


import com.kbank.backend.enumerate.Role;

public record JwtUserInfo(Long id, Role role) {

}
