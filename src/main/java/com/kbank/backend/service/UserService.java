package com.kbank.backend.service;

import com.kbank.backend.domain.User;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {

    private final UserRepository userRepository;

    public Boolean createUser(UserRequestDto userRequestDto) {

        // 시큐리티 후 수정
        User newUser = User.builder()
                .userNm(userRequestDto.getUserNm())
                .phoneNo(userRequestDto.getPhoneNo())
                .gender(userRequestDto.getGender())
                .firstNo(userRequestDto.getFirstNo())
                .lastNo(userRequestDto.getLastNo())
                .bankNm(userRequestDto.getBankNm())
                .accountNo(userRequestDto.getAccountNo())
                .accountPw(userRequestDto.getAccountPw())
                .build();

        userRepository.save(newUser);

        return Boolean.TRUE;
    }
}
