package com.kbank.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.User;
import com.kbank.backend.enumerate.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private String userNm;
    private String phoneNo;
    private Gender gender;
    private String firstNo;
    private String lastNo;
    private String bankNm;
    private Long account;
    private String accountNo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime morningAlarm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime lunchAlarm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime dinnerAlarm;

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .userNm(user.getUserNm())
                .phoneNo(user.getPhoneNo())
                .gender(user.getGender())
                .firstNo(user.getFirstNo())
                .lastNo(user.getLastNo())
                .bankNm(user.getBankNm())
                .account(user.getAccount())
                .accountNo(user.getAccountNo())
                .morningAlarm(user.getMorningAlarm())
                .lunchAlarm(user.getLunchAlarm())
                .dinnerAlarm(user.getDinnerAlarm())
                .build();
    }
}
