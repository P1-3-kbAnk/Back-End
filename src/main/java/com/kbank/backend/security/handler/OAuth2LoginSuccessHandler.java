package com.kbank.backend.security.handler;

import com.kbank.backend.dto.JwtTokenDto;
import com.kbank.backend.enumerate.Role;
import com.kbank.backend.security.info.UserPrincipal;
import com.kbank.backend.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private static final String REDIRECT_URL = "http://localhost:5173/redirect";
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(userPrincipal.getId(), userPrincipal.getRole());

        if (userPrincipal.getRole().equals(Role.GUEST)) {
            response.sendRedirect( REDIRECT_URL + "?accessToken=" + jwtTokenDto.getAccessToken() + "&role=" + userPrincipal.getRole() + "&state=register");
        } else if (userPrincipal.getRole().equals(Role.DOCTOR)) {
            response.sendRedirect( REDIRECT_URL + "?accessToken=" + jwtTokenDto.getAccessToken() + "&role=" + userPrincipal.getRole() + "&state=doctor");
        } else if (userPrincipal.getRole().equals(Role.CHEMIST)) {
            response.sendRedirect( REDIRECT_URL + "?accessToken=" + jwtTokenDto.getAccessToken() + "&role=" + userPrincipal.getRole() + "&state=chemist");
        } else {
            response.sendRedirect(REDIRECT_URL + "?accessToken=" + jwtTokenDto.getAccessToken() + "&role=" + userPrincipal.getRole() + "&state=user");
        }

    }
}
