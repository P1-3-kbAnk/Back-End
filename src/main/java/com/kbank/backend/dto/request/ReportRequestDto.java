package com.kbank.backend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportRequestDto {

    @NotEmpty
    private List<String> caution;
    @NotEmpty
    private List<String> exercise;
    @NotEmpty
    private List<String> food;

}
