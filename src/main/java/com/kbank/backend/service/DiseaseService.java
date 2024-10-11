package com.kbank.backend.service;


import com.kbank.backend.dto.response.DiseaseResponseDto;
import com.kbank.backend.repository.DiseaseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// 수정 요함
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    @Transactional
    public Map<String, Object> getDiseaseList() {
        Map<String, Object> result = new HashMap<>();

        result.put("diseaseList", diseaseRepository.findAll().stream()
                .map(DiseaseResponseDto::fromEntity)
                .toList());

        return result;
    }
}
