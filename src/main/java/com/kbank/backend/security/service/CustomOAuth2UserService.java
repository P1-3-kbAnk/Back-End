package com.kbank.backend.security.service;


import com.kbank.backend.domain.User;
import com.kbank.backend.enumerate.Provider;
import com.kbank.backend.enumerate.ProviderFactory;
import com.kbank.backend.repository.UserRepository;
import com.kbank.backend.security.info.OAuth2UserInfo;
import com.kbank.backend.security.info.OAuth2UserInfoFactory;
import com.kbank.backend.security.info.UserPrincipal;
import com.kbank.backend.enumerate.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
         try {
             return process(userRequest, super.loadUser(userRequest));
        }catch (Exception ex){
             ex.printStackTrace();
             throw ex;
         }
    }
    public OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User){
        Provider provider = ProviderFactory.of(userRequest.getClientRegistration().getRegistrationId().toUpperCase(Locale.ROOT));
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider,oAuth2User.getAttributes());

        UserRepository.UserSecurityForm userSecurityForm = userRepository.findBySocialIdAndEProvider(userInfo.getId(),provider)
                .orElseGet(()->
                {
                    User user = userRepository.save(
                            User.builder()
                                    .socialId(userInfo.getId())
                                    .role(Role.GUEST)
                                    .provider(provider)
                                    .build()
                    );
                    return UserRepository.UserSecurityForm.invoke(user);
                });

        return UserPrincipal.create(userSecurityForm,oAuth2User.getAttributes());
    }
}
