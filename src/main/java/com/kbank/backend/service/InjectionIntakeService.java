package com.kbank.backend.service;


import com.kbank.backend.domain.*;
import com.kbank.backend.dto.response.InjectionIntakeResponseDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.InjectionIntakeRepository;
import com.kbank.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class InjectionIntakeService {

    private final UserRepository userRepository;
    private final InjectionIntakeRepository injectionIntakeRepository;

    public Map<String, Object> getInjectionIntakeByDate(Long userPk, LocalDate date) {

        List<InjectionIntake> injectionIntakeList = injectionIntakeRepository
                .findByInjInkUserAndDay(
                        userRepository.findByUserWithAuthUserId(userPk)
                                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER)),
                        date
                );
        List<InjectionIntakeResponseDto> injectionIntakeResponseDtoList = injectionIntakeList.stream()
                .map(InjectionIntakeResponseDto::fromEntity)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("injectionIntakeList", injectionIntakeResponseDtoList);

        return response;
    }

    public Boolean updateEatSt(Long injInkPk) {
        InjectionIntake injectionIntake = injectionIntakeRepository.findById(injInkPk)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        injectionIntake.updateEatSt();

        return Boolean.TRUE;
    }

}