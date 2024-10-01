package com.kbank.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportRequestDto {

    @NotBlank
    private List<String> caution;
    @NotBlank
    private List<String> exercise;
    @NotBlank
    private List<String> food;

}
