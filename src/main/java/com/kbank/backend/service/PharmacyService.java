package com.kbank.backend.service;

import com.kbank.backend.repository.DiseaseRepository;
import com.kbank.backend.repository.PrescriptionMedicineRepository;
import com.kbank.backend.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PharmacyService {
    private final PrescriptionRepository prescriptionRepository;
    private final DiseaseRepository diseaseRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;

    // 수정 요함
//    @Transactional
//    public boolean updatePrescriptionSt(long id) {
//        Prescription prescription = prescriptionRepository.findById(id)
//                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
//        //반대 상태로 바꾸기
//        prescription.setPrescriptionSt(!prescription.isPrescriptionSt());
//        prescriptionRepository.save(prescription);
//        return true;
//    }
//
//
//    //처방 완료 -> 약국 id랑 같은 것 중에서
//    @Transactional
//    public List<PrescriptionHtmlResponseDto> receivedPrescription(Long chemistId) {
//        // 약사 ID에 해당하는 처방전 조회
//        List<Prescription> prescriptions = prescriptionRepository.findByPreChemist_ChemistPkAndPrescriptionStTrue(chemistId);
//
//        List<PrescriptionHtmlResponseDto> prescriptionResponseList = new ArrayList<>();
//
//        for (Prescription prescription : prescriptions) {
//
//
//            //제조여부 false면 ㄴㄴ
//            if (!prescription.isPrescriptionSt()) continue;
//
//            User user = prescription.getPreUser();
//            Doctor doctor = prescription.getPreDoctor();
//            Hospital hospital = doctor.getDoctorHospital();
//
//            // 질병 및 약 정보 조회
//            List<Disease> diseases = diseaseRepository.findByDiseasePrescription(prescription);
//            List<Medicine> medicines = prescriptionMedicineRepository.findMedicineByPrescription(prescription);
//
//            // DTO 리스트 생성
//            List<MedicineDto> medicineList = new ArrayList<>();
//            for (Medicine medicine : medicines) {
//                medicineList.add(MedicineDto.fromEntity(medicine)); // DTO 변환
//            }
//
//            List<String> diseaseList = new ArrayList<>();
//            for (Disease disease : diseases) {
//                diseaseList.add(disease.getDiseaseCd()); // 질병 코드 추가
//            }
//
//            // 리스트에 추가
//            prescriptionResponseList.add(PrescriptionHtmlResponseDto.fromEntity(
//                    prescription, user, doctor, hospital, diseaseList, medicineList
//            ));
//        }
//
//        return prescriptionResponseList; // 필터링된 처방전 리스트 반환
//    }

}
