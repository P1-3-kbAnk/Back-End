package com.kbank.backend.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayResponseDto {

    //계좌 잔액, 차감될 약값
    private Long account;
    private Long totalPrice;


    public static PayResponseDto toEntity(Long account, Long totalPrice) {
        return PayResponseDto.builder()
                .account(account)
                .totalPrice(totalPrice)
                .build();
    }


}
