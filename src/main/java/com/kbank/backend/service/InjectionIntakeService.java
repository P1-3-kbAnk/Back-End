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
@RequiredArgsConstructor
public class InjectionIntakeService {

    private final UserRepository userRepository;
    private final InjectionIntakeRepository injectionIntakeRepository;




    // 날짜(2024-10-5)를 받아서 조회
    @Transactional
    public Map<String, Object> getInjectionIntakeByDate(Long userPk, LocalDate date) {

        List<InjectionIntake> injectionIntakeList = injectionIntakeRepository
                .findByInjInkUserAndDay(
                        userRepository.findById(userPk).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER)),
                        date
                );

        List<InjectionIntakeResponseDto> injectionIntakeResponseDtoList = new ArrayList<>();

        injectionIntakeList.forEach(injectionIntake -> {
            injectionIntakeResponseDtoList.add(InjectionIntakeResponseDto.fromEntity(injectionIntake));
        });

        Map<String, Object> response = new HashMap<>();

        response.put("injectionIntakeList", injectionIntakeResponseDtoList);

        return response;
    }

    // 복용 여부(EatSt)만 업데이트
    public Boolean updateEatSt(Long userPk, Long injInkPk) {
        User user = userRepository.findById(userPk).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        InjectionIntake injectionIntake = injectionIntakeRepository.findById(injInkPk)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        injectionIntake.updateEatSt();
        injectionIntakeRepository.save(injectionIntake);

        return Boolean.TRUE;
    }

}
//    public List<InjectionIntake> getAllByUser(User user) {
//        return InjectionIntakeRepository.findByInjInkUser(user);
//    }
//
//    public void create(User user, Injection injection, Meal meal) {
//
//        repository.save(InjectionIntake
//                .builder()
//                        .injInkUser(user)
//                        .injInkInjection(injection)
//                        .meal(meal)
//                .build());
//    }