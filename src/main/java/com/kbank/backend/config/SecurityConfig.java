//package com.kbank.backend.config;
//
//import com.kbank.backend.constant.Constant;
//import com.kbank.backend.enumerate.Role;
//import com.kbank.backend.security.JwtAuthEntryPoint;
//import com.kbank.backend.security.JwtAuthenticationProvider;
//import com.kbank.backend.security.filter.JwtAuthenticationFilter;
//import com.kbank.backend.security.filter.JwtExceptionFilter;
//import com.kbank.backend.security.handler.*;
//import com.kbank.backend.security.service.CustomOAuth2UserService;
//import com.kbank.backend.security.service.CustomUserDetailService;
//import com.kbank.backend.util.JwtUtil;
//import jakarta.servlet.annotation.WebListener;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutFilter;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    // Kakao OAuth2 프로퍼티에서 값 가져오기
//    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
//    private String clientId;
//
//    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
//    private String clientSecret;
//
//    @Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
//    private String authorizationUri;
//
//    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
//    private String tokenUri;
//
//    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
//    private String userInfoUri;
//
//    @Value("${spring.security.oauth2.client.provider.kakao.user-name-attribute}")
//    private String userNameAttribute;
//
//    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
//    private String redirectUri;
//
//
//    @Value("${spring.security.oauth2.client.registration.kakao.scope}")
//    private String scope;
//
//    @Bean
//    public ClientRegistration kakaoClientRegistration() {
//        return ClientRegistration.withRegistrationId("kakao")
//                .clientName("kakao")
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .authorizationUri(authorizationUri)
//                .tokenUri(tokenUri)
//                .userInfoUri(userInfoUri)
//                .userNameAttributeName(userNameAttribute)
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
//                .redirectUri(redirectUri)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .scope(scope)
//                .build();
//    }
//
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(kakaoClientRegistration());
//    }
//
//    private final CustomUserDetailService customUserDetailService;
//    private final JwtUtil jwtUtil;
//    private final JwtAuthEntryPoint jwtAuthEntryPoint;
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final LogoutHandler customLogOutProcessHandler;
//    private final CustomLogOutResultHandler customLogOutResultHandler;
//    private final AuthenticationSuccessHandler oAuth2LoginSuccessHandler;
//    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("a")
////                .password(passwordEncoder().encode("a"))
////                .roles("ADMIN");
////    }
//
//    @Bean
//    protected SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
//
//        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable)
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .sessionManagement((sessionManagement) ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(requestMatcherRegistry -> requestMatcherRegistry
//                        .requestMatchers("api/v1/auth/**").permitAll()
//                        .requestMatchers("api/v1/users/**").hasAnyRole((Role.USER.toString()), Role.ADMIN.toString())
//                        .requestMatchers("api/v1/admin/**").hasRole(Role.ADMIN.toString())
//                        .anyRequest().authenticated())
//                .oauth2Login(
//                        configurer ->
//                                configurer
//                                        .successHandler(oAuth2LoginSuccessHandler)
//                                        .failureHandler(oAuth2LoginFailureHandler)
//                                        .userInfoEndpoint(userInfoEndpoint ->
//                                                userInfoEndpoint.userService(customOAuth2UserService))
//                )
//                .logout(configurer ->
//                        configurer
//                                .logoutUrl("/api/v1/auth/logout")
//                                .addLogoutHandler(customLogOutProcessHandler)
//                                .logoutSuccessHandler(customLogOutResultHandler)
//                                .deleteCookies(Constant.AUTHORIZATION, Constant.REAUTHORIZATION)
//                )
//                .exceptionHandling(configurer ->
//                        configurer
//                                .authenticationEntryPoint(jwtAuthEntryPoint)
//                                .accessDeniedHandler(jwtAccessDeniedHandler)
//                )
//                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, new JwtAuthenticationProvider(customUserDetailService)), LogoutFilter.class)
//                .addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class)
//                .getOrBuild();
//    }
//}
