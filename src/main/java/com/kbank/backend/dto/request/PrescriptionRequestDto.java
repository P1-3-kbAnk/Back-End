package com.kbank.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRequestDto {
    private long doctorId;  // 명확한 변수명으로 변경
    private long userId;    // 명확한 변수명으로 변경
    private long chemistId; // 명확한 변수명으로 변경

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createYmd;

    private int prescriptionNo;
    private int duration;
    private String description;
    private boolean prescriptionSt;
    private boolean insuranceSt;


}
