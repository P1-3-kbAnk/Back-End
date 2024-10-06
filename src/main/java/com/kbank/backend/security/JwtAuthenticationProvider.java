package com.kbank.backend.security;

import com.kbank.backend.security.info.JwtUserInfo;
import com.kbank.backend.security.info.UserPrincipal;
import com.kbank.backend.security.service.CustomUserDetailService;
import com.kbank.backend.enumerate.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailService customUserDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserPrincipal userPrincipal = null;

        JwtUserInfo jwtUserInfo = new JwtUserInfo((Long) authentication.getPrincipal(), (Role) authentication.getCredentials());
        userPrincipal = (UserPrincipal) customUserDetailService.loadUserByUserId(jwtUserInfo.id());

        if (userPrincipal.getRole() != jwtUserInfo.role()) {
            throw new AuthenticationException("Invalid Role") {
            };
        }


        return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
