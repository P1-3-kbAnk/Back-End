package com.kbank.backend.security.config;

import com.kbank.backend.constant.Constant;
import com.kbank.backend.enumerate.Role;
import com.kbank.backend.security.JwtAuthEntryPoint;
import com.kbank.backend.security.JwtAuthenticationProvider;
import com.kbank.backend.security.filter.JwtAuthenticationFilter;
import com.kbank.backend.security.filter.JwtExceptionFilter;
import com.kbank.backend.security.handler.CustomLogOutResultHandler;
import com.kbank.backend.security.handler.JwtAccessDeniedHandler;
import com.kbank.backend.security.handler.OAuth2LoginFailureHandler;
import com.kbank.backend.security.service.CustomOAuth2UserService;
import com.kbank.backend.security.service.CustomUserDetailService;
import com.kbank.backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailService customUserDetailService;
    private final JwtUtil jwtUtil;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final LogoutHandler customLogOutProcessHandler;
    private final CustomLogOutResultHandler customLogOutResultHandler;
    private final AuthenticationSuccessHandler oAuth2LoginSuccessHandler;
    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("a")
//                .password(passwordEncoder().encode("a"))
//                .roles("ADMIN");
//    }

    @Bean
    protected SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requestMatcherRegistry -> requestMatcherRegistry
                        .requestMatchers("api/auth/**").permitAll()
                        .requestMatchers(Constant.NO_NEED_AUTH_URLS.toArray(String[]::new)).permitAll()
                        .requestMatchers("api/v1/users/**").hasAnyRole((Role.USER.toString()), Role.ADMIN.toString())
                        .requestMatchers("api/v1/admin/**").hasRole(Role.ADMIN.toString())
                        .anyRequest().authenticated())
                .oauth2Login(
                        configurer ->
                                configurer
                                        .successHandler(oAuth2LoginSuccessHandler)
                                        .failureHandler(oAuth2LoginFailureHandler)
                                        .userInfoEndpoint(userInfoEndpoint ->
                                                userInfoEndpoint.userService(customOAuth2UserService))
                )
                .logout(configurer ->
                        configurer
                                .logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(customLogOutProcessHandler)
                                .logoutSuccessHandler(customLogOutResultHandler)
                                .deleteCookies(Constant.AUTHORIZATION, Constant.REAUTHORIZATION)
                )
                .exceptionHandling(configurer ->
                        configurer
                                .authenticationEntryPoint(jwtAuthEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, new JwtAuthenticationProvider(customUserDetailService)), LogoutFilter.class)
                .addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class)
                .getOrBuild();
    }

}
