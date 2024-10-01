package com.kbank.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRequestDto {

    @NotBlank
    private Integer prescriptionNo;
    @NotBlank
    private Integer duration;
    @NotBlank
    private String description;
    @NotBlank
    private boolean prescriptionSt;
    @NotBlank
    private boolean insuranceSt;

}
