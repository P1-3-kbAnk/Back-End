package com.kbank.backend.security.handler;

import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.security.AbstractAuthenticationFailure;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAccessDeniedHandler extends AbstractAuthenticationFailure implements AccessDeniedHandler {

    @Override
    public void handle(final HttpServletRequest request,
                       final HttpServletResponse response,
                       final AccessDeniedException accessDeniedException) throws IOException {
        setErrorResponse(response, ErrorCode.ACCESS_DENIED_ERROR);
    }

}
