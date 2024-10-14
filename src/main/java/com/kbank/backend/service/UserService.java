package com.kbank.backend.service;

import com.kbank.backend.domain.User;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.dto.response.UserResponseDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {

    private final UserRepository userRepository;

    /**userId로 정보얻기**/
    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_USER));

        return UserResponseDto.fromEntity(user);
    }
    /**계좌정보 수정**/
    public boolean  updateAccountInfo(Long userId, UserRequestDto userRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_USER));

        if(!userRequestDto.getAccountPw().equals(user.getAccountPw())) return Boolean.FALSE;

        user.updateBankNm(userRequestDto.getBankNm());
        user.updateAccountNo(userRequestDto.getAccountNo());
        userRepository.save(user);
        return Boolean.TRUE;
    }

    /**알람시간 변경**/
    public boolean updateAlarm(Long userId,UserRequestDto userRequestDto){
        User user=userRepository.findByUserPk(userId)
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_USER));
        // 시큐리티 후 수정
        user.updateAlarm(userRequestDto);
        userRepository.save(user);
        return Boolean.TRUE;
    }
}
