package com.kbank.backend.service;

import com.kbank.backend.domain.Injection;
import com.kbank.backend.dto.response.InjectionResponseDto;
import com.kbank.backend.repository.InjectionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class InjectionService {
    private final InjectionRepository injectionRepository;

    public Map<String, Object> getInjectionList() {
        Map<String, Object> result = new HashMap<>();
        List<Injection> injectionList = injectionRepository.findAll();
        List<InjectionResponseDto> injectionResponseDtoList = injectionList.stream()
                .map(InjectionResponseDto::fromEntity)
                .toList();

        result.put("injectionList", injectionResponseDtoList);

        return result;
    }
}
