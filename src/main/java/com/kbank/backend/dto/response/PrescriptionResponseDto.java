package com.kbank.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.dto.*;
import com.kbank.backend.dto.request.DiseaseRequestDto;
import com.kbank.backend.dto.request.PrescriptionMedicineRequestDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrescriptionResponseDto {
    private long preDoctorFk;
    private long preUserFk;
    private long preChemistFk;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createYmd;

    private int prescriptionNo;
    private int duration;
    private String description;
    private boolean prescriptionSt;
    private boolean insuranceSt;

    private List<DiseaseRequestDto> diseaseRequestDtos;  // 질병 목록 추가
//    private List<PrescriptionMedicineRequestDto> preMedReqDtos;


// Prescription 엔티티를 DTO로 변환하는 메서드
    public static PrescriptionResponseDto toEntity(Prescription prescription,List<DiseaseRequestDto> diseaseDtos) {
        return PrescriptionResponseDto
                .builder()
                .preDoctorFk(prescription.getPreDoctor().getDoctorPk())
                .preUserFk(prescription.getPreUser().getUserPk())
                .preChemistFk(prescription.getPreChemist().getChemistPk())
                .createYmd(prescription.getCreateYmd())  // createYmd 필드 매핑
                .prescriptionNo(prescription.getPrescriptionNo())  // prescriptionNo 필드 매핑
                .duration(prescription.getDuration())  // duration 필드 매핑
                .description(prescription.getDescription())  // description 필드 매핑
                .prescriptionSt(prescription.isPrescriptionSt())  // prescriptionSt 필드 매핑
                .insuranceSt(prescription.isInsuranceSt())  // insuranceSt 필드 매핑
                .diseaseRequestDtos(diseaseDtos)
//                .preMedReqDtos(preMedReqDtos)
                .build();
    }



}
