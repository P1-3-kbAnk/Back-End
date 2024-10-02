package com.kbank.backend.dto.response;

import com.kbank.backend.domain.Pharmacy;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PharmacyResponseDto {

    private Long pharmacyPk;
    private String pharmacyNm;
    private String phoneNo;
    private String faxNo;
    private Long pharmacyNo;
    private Long dongId;

    public static PharmacyResponseDto toEntity(Pharmacy pharmacy, Long dongId) {
        return PharmacyResponseDto.builder()
                .pharmacyPk(pharmacy.getPharmacyPk())
                .pharmacyNm(pharmacy.getPharmacyNm())
                .phoneNo(pharmacy.getPhoneNo())
                .faxNo(pharmacy.getFaxNo())
                .pharmacyNo(pharmacy.getPharmacyNo())
                .dongId(dongId)
                .build();
    }
}
