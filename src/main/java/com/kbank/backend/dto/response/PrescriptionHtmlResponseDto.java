package com.kbank.backend.dto.response;


import com.kbank.backend.domain.Doctor;
import com.kbank.backend.domain.Hospital;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.User;
import com.kbank.backend.dto.MedicineDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PrescriptionHtmlResponseDto {

    private int prescriptionNo;
    private int duration;
    private LocalDateTime createYmd;

    private String hospitalNm;
    private String phoneNo;
    private String faxNo;

    private String tp;
    private String doctorNm;
    private String doctorNo;

    private String userNm;
    private String firstNo;
    private String lastNoFC;

    private List<String> diseaseCd;

    private List<MedicineDto> medicineList;

    private String chemistNm;

    @Builder
    public PrescriptionHtmlResponseDto(int prescriptionNo, int duration, LocalDateTime createYmd, String hospitalNm, String phoneNo, String faxNo, String tp, String doctorNm, String doctorNo, String userNm, String firstNo, String lastNoFC, List<String> diseaseCd, List<MedicineDto> medicineList) {
        this.prescriptionNo = prescriptionNo;
        this.duration = duration;
        this.createYmd = createYmd;
        this.hospitalNm = hospitalNm;
        this.phoneNo = phoneNo;
        this.faxNo = faxNo;
        this.tp = tp;
        this.doctorNm = doctorNm;
        this.doctorNo = doctorNo;
        this.userNm = userNm;
        this.firstNo = firstNo;
        this.lastNoFC = lastNoFC;
        this.diseaseCd = diseaseCd;
        this.medicineList = medicineList;
    }

    public void setChemist(String chemistNm) {
        this.chemistNm = chemistNm;
    }

    public static PrescriptionHtmlResponseDto toEntity(Prescription prescription, User user, Doctor doctor, Hospital hospital, List<String> diseaseList, List<MedicineDto> medicineList) {
        return PrescriptionHtmlResponseDto
                .builder()
                .prescriptionNo(prescription.getPrescriptionNo())
                .duration(prescription.getDuration())
                .createYmd(prescription.getCreateYmd())
                .hospitalNm(hospital.getHospitalNm())
                .phoneNo(hospital.getPhoneNo())
                .faxNo(hospital.getFaxNo())
                .tp(doctor.getTp().name())
                .doctorNm(doctor.getDoctorNm())
                .doctorNo(doctor.getDoctorNo())
                .userNm(user.getUserNm())
                .firstNo(user.getFirstNo())
                .lastNoFC(user.getLastNo().substring(0,0))
                .diseaseCd(diseaseList)
                .medicineList(medicineList)
                .build();
    }
}
