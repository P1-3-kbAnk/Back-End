package com.kbank.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.dto.DiseaseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<DiseaseRequestDto> diseases;  // 질병 정보를 리스트로 포함
//    private List<PrescriptionMedicineRequestDto> preMedReqDtos;

//    @Builder
//    public PrescriptionRequestDto(long preDoctorFk, long preUserFk, long preChemistFk,
//                                  int prescriptionNo, int duration, String description,
//                                  boolean prescriptionSt, boolean insuranceSt, List<DiseaseRequestDto> diseases) {
//        this.doctorId = preDoctorFk;
//        this.userId = preUserFk;
//        this.chemistId = preChemistFk;
//        this.prescriptionNo = prescriptionNo;
//        this.duration = duration;
//        this.description = description;
//        this.prescriptionSt = prescriptionSt;
//        this.insuranceSt = insuranceSt;
//        this.diseases = diseases;
//    }



}
