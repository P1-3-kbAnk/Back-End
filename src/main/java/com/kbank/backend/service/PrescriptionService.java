package com.kbank.backend.service;

import com.kbank.backend.domain.*;
import com.kbank.backend.dto.MedicineDto;
import com.kbank.backend.dto.request.PrescriptionHtmlRequestDto;
import com.kbank.backend.dto.response.PrescriptionHtmlResponseDto;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final ChemistRepository chemistRepository;
    private final DiseaseRepository diseaseRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;
    private final HospitalRepository hospitalRepository;
    private final MedicineRepository medicineRepository;

    @Transactional
    public boolean updateInsuranceSt(long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
        //반대 상태로 바꾸기
        prescription.setInsuranceSt(!prescription.isInsuranceSt());
        prescriptionRepository.save(prescription);
        return true;
    }


    //처방 받아야 할 리스트 조회
    @Transactional
    public List<PrescriptionHtmlResponseDto> notReceivedPrescriptionHtmls() {
        // 모든 처방전 조회
        List<Prescription> prescriptions = prescriptionRepository.findAll();

        List<PrescriptionHtmlResponseDto> prescriptionResponseList = new ArrayList<>();

        for (Prescription prescription : prescriptions) {

            // 현재 시간이 유효기간 안에 있는지 비교
            if (!LocalDateTime.now().isBefore(prescription.getCreateYmd().plusDays(prescription.getDuration()))) continue;
            //처방여부 true면 ㄴㄴㄴ
            if(prescription.isPrescriptionSt()) continue;;

            User user = prescription.getPreUser();
            Doctor doctor = prescription.getPreDoctor();
            Hospital hospital = doctor.getDoctorHospital();

            // 질병 및 약 정보 조회
            List<Disease> diseases = diseaseRepository.findByDiseasePrescription(prescription);
            List<Medicine> medicines = prescriptionMedicineRepository.findMedicineByPrescription(prescription);

            // DTO 리스트 생성
            List<MedicineDto> medicineList = new ArrayList<>();
            for (Medicine medicine : medicines) {
                medicineList.add(MedicineDto.toEntity(medicine)); // DTO 변환
            }
            //여기도 dto 써야 될까요
            List<String> diseaseList = new ArrayList<>();
            for (Disease disease : diseases) {
                diseaseList.add(disease.getDiseaseCd()); // 질병 코드 추가
            }

            // 리스트에 추가
            prescriptionResponseList.add(PrescriptionHtmlResponseDto.toEntity(
                    prescription,user,doctor,hospital,diseaseList,medicineList
            ));
        }

        return prescriptionResponseList; // 모든 처방전 리스트 반환
    }


    //전체 리스트 조회
    @Transactional
    public List<PrescriptionHtmlResponseDto> getAllPrescriptionHtmls() {
        // 모든 처방전 조회
        List<Prescription> prescriptions = prescriptionRepository.findAll();

        List<PrescriptionHtmlResponseDto> prescriptionResponseList = new ArrayList<>();

        for (Prescription prescription : prescriptions) {
            User user = prescription.getPreUser();
            Doctor doctor = prescription.getPreDoctor();
            Hospital hospital = doctor.getDoctorHospital();

            // 질병 및 약 정보 조회
            List<Disease> diseases = diseaseRepository.findByDiseasePrescription(prescription);
            List<Medicine> medicines = prescriptionMedicineRepository.findMedicineByPrescription(prescription);

            // DTO 리스트 생성
            List<MedicineDto> medicineList = new ArrayList<>();
            for (Medicine medicine : medicines) {
                medicineList.add(MedicineDto.toEntity(medicine)); // DTO 변환
            }
            //여기도 dto 써야 될까요
            List<String> diseaseList = new ArrayList<>();
            for (Disease disease : diseases) {
                diseaseList.add(disease.getDiseaseCd()); // 질병 코드 추가
            }

            // 리스트에 추가
            prescriptionResponseList.add(PrescriptionHtmlResponseDto.toEntity(
                    prescription,user,doctor,hospital,diseaseList,medicineList
            ));
        }

        return prescriptionResponseList; // 모든 처방전 리스트 반환
    }


    @Transactional
    public PrescriptionHtmlResponseDto getPrescriptionHtml(Long id) {
        Prescription prescription = prescriptionRepository
                .findById(id)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        User user = prescription.getPreUser();

        Doctor doctor = prescription.getPreDoctor();

        Hospital hospital = doctor.getDoctorHospital();

        List<Disease> disease = diseaseRepository.findByDiseasePrescription(prescription);

        List<Medicine> medicine = prescriptionMedicineRepository
                .findMedicineByPrescription(prescription);

        List<MedicineDto> medicineList = new ArrayList<>();
        List<String> diseaseList = new ArrayList<>();

        medicine.forEach(e -> medicineList.add(MedicineDto.toEntity(e)));
        disease.forEach(e -> diseaseList.add(e.getDiseaseCd()));


        return PrescriptionHtmlResponseDto.toEntity(prescription, user, doctor, hospital, diseaseList, medicineList);
    }

    @Transactional
    public boolean createPrescription(PrescriptionHtmlRequestDto preHtmlReqDto)
    {
        //처방전에 입력받은 정보로 create할 때 각 테이블 pk번호를 받는게 아니라서 ..  findfirst안하면 무조건 중복된 데이터가 있을 수 밖에 없음..
        //데이터를 중복해서 안넣거나 첫번째 찾는 값을 사용해야 될 것 같은디.. 좋은 생각 있나요...
        // 병원 정보 확인
        Hospital hospital = hospitalRepository.findByHospitalNm(preHtmlReqDto.getHospitalNm())
                .stream()
                .findFirst()
                .orElseThrow(() ->new CommonException(ErrorCode.NOT_FOUND_HOSPITAL));

        // 의사 정보 확인
        Doctor doctor = doctorRepository.findByDoctorNo(preHtmlReqDto.getDoctorNo())
                .stream()
                .findFirst()
                .orElseThrow(() ->new CommonException(ErrorCode.NOT_FOUND_DOCTOR));

        // 사용자 정보 확인
        User user = userRepository.findByUserNm(preHtmlReqDto.getUserNm())
                .stream()
                .findFirst()
                .orElseThrow(() ->new CommonException(ErrorCode.NOT_FOUND_USER));

        // 약사 정보 확인 (null 허용)
        Chemist chemist = null;
        if (preHtmlReqDto.getChemistNm() != null) {
            chemist = chemistRepository.findByChemistNm(preHtmlReqDto.getChemistNm())
                    .stream()
                    .findFirst()
                    .orElseThrow(() ->new CommonException(ErrorCode.NOT_FOUND_CHEMIST));
        }

        // 2. Prescription 엔티티 생성 및 저장
        Prescription prescription = Prescription.builder()
                .preDoctor(doctor)
                .preUser(user)
                .preChemist(chemist)
                .prescriptionNo(preHtmlReqDto.getPrescriptonNo())
                .duration(preHtmlReqDto.getDuration())
                .description(preHtmlReqDto.getDescription())
                .prescriptionSt(preHtmlReqDto.isPrescriptionSt())
                .insuranceSt(preHtmlReqDto.isInsuranceSt())
                .build();
        prescriptionRepository.save(prescription);

        // 3. 관련된 질병 정보 저장
        List<Disease> diseases = preHtmlReqDto.getDiseaseList().stream()
                .map(diseaseDto -> Disease.builder()
                        .diseaseCd(diseaseDto.getDiseaseCd())
                        .diseasePrescription(prescription)
                        .build())
                .collect(Collectors.toList());
        diseaseRepository.saveAll(diseases);

        // 4. 약 정보 저장
        List<Medicine> medicines = preHtmlReqDto.getMedicineList().stream()
                .map(medicineDto -> Medicine.builder()
                        .medicineNm(medicineDto.getMedicineNm())
                        .dosePerTime(medicineDto.getDoesPerTime())
                        .dosePerDay(medicineDto.getDoesPerDay())
                        .unit(medicineDto.getUsage())
                        .method(medicineDto.getMethod())
                        .medicineCd(medicineDto.getMedicineCd())
                        .price(medicineDto.getPrice())
                        .caution(medicineDto.getCaution())
                        .sideEffect(medicineDto.getSideEffect())
                        .efficacy(medicineDto.getEfficacy())
                        .unit(medicineDto.getUnit())
                        .time(medicineDto.getTime())
                        .imageUrl(medicineDto.getImageUrl())
                        .copaymentRateCd(medicineDto.getCopaymentRateCd())
                        .build())
                .collect(Collectors.toList());

        medicineRepository.saveAll(medicines);

        // 5. PrescriptionMedicine 저장 (처방전과 약 매핑)
        List<PrescriptionMedicine> prescriptionMedicines = medicines.stream()
                .map(medicine -> PrescriptionMedicine.builder()
                        .totalDays(preHtmlReqDto.getDuration())  // 처방된 기간 사용
                        .preMedPrescription(prescription)       // 처방전과 연결
                        .preMedMedicine(medicine)               // 약과 연결
                        .build())
                .collect(Collectors.toList());

        prescriptionMedicineRepository.saveAll(prescriptionMedicines);

        // 트랜잭션 성공적으로 완료
        return true;

    }



//    // 처방전 교부번호를 기준으로 조회 -> 처방전의 주키를 반환 (근데 처방전 교부 번호를 기준으로 조회하는 API를 만들면 되지 않나?
//    public QrResponse getPreQRByPreNo(Long userPk, int prescriptionNo) {
//        Prescription prescription = prescriptionRepository
//                .findByPrescriptionNo(prescriptionNo)
//                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
//
//        return QrResponse.toEntity(prescription.getPrescriptionPk());
//    }


}
