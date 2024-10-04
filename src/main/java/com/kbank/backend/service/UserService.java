package com.kbank.backend.service;

import com.kbank.backend.domain.User;
import com.kbank.backend.dto.request.AccountRequestDto;
import com.kbank.backend.dto.request.MedicineTimeDto;
import com.kbank.backend.dto.request.UserRequestDto;
import com.kbank.backend.dto.response.UserResponseDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_USER));
        return UserResponseDto.toEntity(user);
    }
    //계좌 업데이트
    @Transactional
    public Map<?,?> updateUser(Long userId, AccountRequestDto accountRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_USER));

        user.setBankNm(accountRequestDto.getBankNm());
        user.setAccountNo(accountRequestDto.getAccountNo());

        Map<String, String> result = new HashMap<>();
        result.put("bankNm", String.valueOf(accountRequestDto.getBankNm()));
        result.put("accountNo", String.valueOf(accountRequestDto.getAccountNo()));

        return result;
    }

    //복약 시간 업데이트
    @Transactional
    public Map<?,?> updateMedicineTime(Long userId, MedicineTimeDto medicineTimeDto){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new CommonException(ErrorCode.NOT_FOUND_USER));

        user.setMorningAlarm(medicineTimeDto.getMorningAlarm());
        user.setLunchAlarm(medicineTimeDto.getLunchAlarm());
        user.setDinnerAlarm(medicineTimeDto.getDinnerAlarm());
        Map<String, String> result = new HashMap<>();

        result.put("morningAlarm", String.valueOf(user.getMorningAlarm()));
        result.put("lunchAlarm", String.valueOf(user.getLunchAlarm()));
        result.put("dinnerAlarm", String.valueOf(user.getDinnerAlarm()));

        return result;
    }


}
