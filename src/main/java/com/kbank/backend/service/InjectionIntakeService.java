package com.kbank.backend.service;


import com.kbank.backend.domain.*;
import com.kbank.backend.dto.response.InjectionIntakeResponseDto;
import com.kbank.backend.dto.response.InjectionResponseDto;
import com.kbank.backend.dto.response.MedicineIntakeResponseDto;
import com.kbank.backend.dto.response.MedicineResponseDto;
import com.kbank.backend.enumerate.Meal;
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
    public List<Map<?, ?>> getInjectionIntakeByDate(Long userPk, String date) {

        String[] tmp = date.split("-");

        LocalDate localDate = LocalDate.of(
                Integer.parseInt(tmp[0]),
                Integer.parseInt(tmp[1]),
                Integer.parseInt(tmp[2])
        );

        List<InjectionIntake> list = injectionIntakeRepository
                .findByInjInkUserAndDay(
                        userRepository.findById(userPk).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER)),
                        localDate
                );

        List<Map<?, ?>> result = new ArrayList<>();

        list.forEach(e -> {
            Map<String,Object> st = new HashMap<>();

            st.put("intake", InjectionIntakeResponseDto
                    .builder()
                    .injInkPk(e.getInjInkPk())
                    .meal(e.getMeal())
                    .day(e.getDay())
                    .eatSt(e.getEatSt())
                    .build()
            );

            st.put("inj", InjectionResponseDto
                    .builder()
                    .injectionPk(e.getInjInkPk())
                    .injectionNm(e.getInjInkInjection().getInjectionNm())
                    .time(e.getInjInkInjection().getTime())
                    .caution(e.getInjInkInjection().getCaution())
                    .build());

            result.add(st);
        });

        return result;
    }

    // 복용 여부(EatSt)만 업데이트
    public Boolean updateEatSt(Long userPk, Long injInkPk) {
        User user = userRepository.findById(userPk).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        InjectionIntake injectionIntake = injectionIntakeRepository.findById(injInkPk)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 해당 유저의 접근이 아닐경우 예외 던지기
        if(injectionIntake.getInjInkUser() != user) {throw new CommonException(ErrorCode.ACCESS_DENIED_ERROR);}

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