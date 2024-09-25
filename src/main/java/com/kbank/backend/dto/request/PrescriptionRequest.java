package com.kbank.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbank.backend.domain.Chemist;
import com.kbank.backend.domain.Doctor;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRequest {
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

    // toEntity 메서드에서 외래키로 엔티티를 생성해서 반환
    public Prescription toEntity(Doctor doctor, User user, Chemist chemist) {
        return Prescription.builder()
                .preDoctorFk(doctor)  // 외래 키 대신 엔티티 주입
                .preUserFk(user)  // 외래 키 대신 엔티티 주입
                .preChemistFk(chemist)  // 외래 키 대신 엔티티 주입
                .createYmd(createYmd)
                .prescriptionNo(prescriptionNo)
                .duration(duration)
                .description(description)
                .prescriptionSt(prescriptionSt)
                .insuranceSt(insuranceSt)
                .build();
    }
}
