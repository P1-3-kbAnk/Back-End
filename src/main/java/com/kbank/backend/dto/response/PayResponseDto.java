package com.kbank.backend.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayResponseDto {

    //계좌 잔액, 차감될 약값
    private Long account;
    private Long totalPrice;

    @Builder
    public PayResponseDto(Long account, Long totalPrice) {
        this.account = account;
        this.totalPrice = totalPrice;
    }

    public static PayResponseDto toEntity(Long account, Long totalPrice) {
        return PayResponseDto.builder()
                .account(account)
                .totalPrice(totalPrice)
                .build();
    }


}
