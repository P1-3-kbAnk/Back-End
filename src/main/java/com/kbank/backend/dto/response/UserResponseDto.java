package com.kbank.backend.dto.response;

import com.kbank.backend.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {

    private String AccountNo;
    private String BankNm;

    @Builder
    public UserResponseDto(String accountNo, String bankNm) {
        this.AccountNo = accountNo;
        this.BankNm = bankNm;

    }

    public static UserResponseDto toEntity(User user){
        return UserResponseDto
                .builder()
                .accountNo(user.getAccountNo())
                .bankNm(user.getBankNm())
                .build();
    }
}
