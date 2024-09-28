package com.kbank.backend.service;

import com.kbank.backend.domain.Chemist;
import com.kbank.backend.domain.Pharmacy;
import com.kbank.backend.dto.request.ChemistRequestDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.ChemistRepository;
import com.kbank.backend.repository.PharmacyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ChemistService {

    private final PharmacyRepository pharmacyRepository;
    private final ChemistRepository chemistRepository;

    public Boolean createChemist(ChemistRequestDto chemistRequestDto) {

        // 시큐리티 후 수정
        Pharmacy pharmacy = pharmacyRepository.findByPharmacyPk(chemistRequestDto.getPharmacyPk())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PHARMACY));
        Chemist newChemist = Chemist.builder()
                .chemistNm(chemistRequestDto.getChemistNm())
                .chemistNo(chemistRequestDto.getChemistNo())
                .phoneNo(chemistRequestDto.getPhoneNo())
                .gender(chemistRequestDto.getGender())
                .chemistPharmacy(pharmacy)
                .build();

        chemistRepository.save(newChemist);

        return Boolean.TRUE;
    }
}
