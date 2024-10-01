package com.kbank.backend.dto.request;

import com.kbank.backend.dto.MedicineDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionHtmlRequestDto {
    // 병원 정보
    private String hospitalNm;  // 병원 이름
    private String phoneNo;     // 병원 전화번호
    private String faxNo;       //

    // 의사 정보
    private String tp;          // 의사 전문 분야 (CARDIOLOGIST 등)
    private String doctorNm;    // 의사 이름
    private String doctorNo;    // 의사 번호

    // 사용자 정보
    private String userNm;      // 사용자 이름
    private String firstNo;     // 사용자 주민번호 앞자리
    private String lastNoFC;    // 주민번호 뒷자리 일부 (암호화된 값)병원 팩스번호

    //처방전 정보
    private LocalDateTime createYmd;
    private int prescriptonNo;
    private int duration;
    private String description;

    //여기는 입력 안받음
    private boolean prescriptionSt=false;
    private boolean insuranceSt=false;

    // 질병 정보
    private List<DiseaseRequestDto> diseaseList;  // 질병 코드 리스트

    // 약 정보
    private List<MedicineDto> medicineList;  // 약 정보 리스트

    // 약사 정보
    private String chemistNm;  // 약사 이름
}
