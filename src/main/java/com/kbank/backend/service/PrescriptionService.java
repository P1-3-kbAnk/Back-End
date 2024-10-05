package com.kbank.backend.service;

import com.kbank.backend.domain.*;
import com.kbank.backend.dto.request.PrescriptionRequestDto;
import com.kbank.backend.dto.response.*;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DiseaseRepository diseaseRepository;
    private final InjectionRepository injectionRepository;
    private final MedicineRepository medicineRepository;
    private final PrescriptionInjectionRepository prescriptionInjectionRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;
    private final PrescriptionDiseaseRepository prescriptionDiseaseRepository;
    private final HospitalBillRepository hospitalBillRepository;


    /** 처방전 생성 &  병원 영수증 생성 */
    @Transactional
    public Boolean createPrescription(Long doctorId, PrescriptionRequestDto prescriptionRequestDto) {
        Doctor doctor = doctorRepository.findByIdWithHospital(doctorId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_DOCTOR));
        User user = userRepository.findById(prescriptionRequestDto.getUserPk())
                .orElseThrow(() ->new CommonException(ErrorCode.NOT_FOUND_USER));

        // Prescription 엔티티 생성 및 저장
        Prescription prescription = Prescription.builder()
                .preDoctor(doctor)
                .preUser(user)
                .preChemist(null)
                .prescriptionNo(prescriptionRequestDto.getPrescriptionNo())
                .duration(prescriptionRequestDto.getDuration())
                .description(prescriptionRequestDto.getDescription())
                .build();
        prescriptionRepository.save(prescription);

        List<Disease> diseaseList = diseaseRepository.findAllById(prescriptionRequestDto.getDiseasePkList());
        if (diseaseList.isEmpty()) {
            throw new CommonException(ErrorCode.MISSING_REQUEST_PARAMETER);
        }

        // 관련된 질병 정보 저장
        List<PrescriptionDisease> newPrescriptionDiseaseList = diseaseList.stream()
                .map(disease -> PrescriptionDisease.builder()
                        .preDisPrescription(prescription)
                        .preDisDisease(disease)
                        .build())
                .toList();
        prescriptionDiseaseRepository.saveAll(newPrescriptionDiseaseList);

        // 주사제, 약 둘다없음 에러 반환
        if (prescriptionRequestDto.getInjectionIntakeInfoList().isEmpty() && prescriptionRequestDto.getMedicineIntakeInfoList().isEmpty()) {
            throw new CommonException(ErrorCode.MISSING_REQUEST_PARAMETER);
        }

        // 약 정보 저장
        if (!prescriptionRequestDto.getMedicineIntakeInfoList().isEmpty()) {
            List<PrescriptionMedicine> prescriptionMedicineList = new ArrayList<>();

            prescriptionRequestDto.getMedicineIntakeInfoList()
                    .forEach(medicineInfo -> {
                        Medicine medicine = medicineRepository.findById(medicineInfo.getMedicinePk())
                                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MEDICINE));
                        PrescriptionMedicine newPrescriptionMedicine = PrescriptionMedicine.builder()
                                .medicineNm(medicine.getMedicineNm())
                                .dosePerMorning(medicineInfo.getDosePerMorning())
                                .dosePerLunch(medicineInfo.getDosePerLunch())
                                .dosePerDinner(medicineInfo.getDosePerDinner())
                                .method(medicineInfo.getMethod())
                                .totalDay(medicineInfo.getTotalDay())
                                .preMedMedicine(medicine)
                                .preMedPrescription(prescription)
                                .build();

                        prescriptionMedicineList.add(newPrescriptionMedicine);
                    });

            prescriptionMedicineRepository.saveAll(prescriptionMedicineList);
        }

        // 주사제 저장
        if (!prescriptionRequestDto.getInjectionIntakeInfoList().isEmpty()) {
            List<PrescriptionInjection> prescriptionInjectionList = new ArrayList<>();

            prescriptionRequestDto.getInjectionIntakeInfoList()
                    .forEach(injectionInfo -> {
                        Injection injection = injectionRepository.findById(injectionInfo.getInjectionPk())
                                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MEDICINE));
                        PrescriptionInjection newPrescriptionInjection = PrescriptionInjection.builder()
                                .injectionNm(injection.getInjectionNm())
                                .dosePerMorning(injectionInfo.getDosePerMorning())
                                .dosePerLunch(injectionInfo.getDosePerLunch())
                                .dosePerDinner(injectionInfo.getDosePerDinner())
                                .method(injectionInfo.getMethod())
                                .totalDay(injectionInfo.getTotalDay())
                                .preInjInjection(injection)
                                .preInjPrescription(prescription)
                                .build();

                        prescriptionInjectionList.add(newPrescriptionInjection);
                    });

            prescriptionInjectionRepository.saveAll(prescriptionInjectionList);
        }

        // 병원 영수증 저장
        HospitalBill newHospitalBill = HospitalBill.builder()
                .hospitalBillHospital(doctor.getDoctorHospital())
                .hospitalBillPrescription(prescription)
                .totalPrice(10000L)
                .build();

        hospitalBillRepository.save(newHospitalBill);

        return Boolean.TRUE;
    }

    /** 전체 리스트 조회 */
    @Transactional
    public Map<String, Object> getAllPrescriptionList(Long userId, Integer pageIndex, Integer pageSize) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Page<Prescription> prescriptionList = prescriptionRepository.findAllByPreUser(
                user,
                PageRequest.of(pageIndex, pageSize, Sort.by(
                        Sort.Order.asc("prescriptionSt"),
                        Sort.Order.desc("createYmd")
                ))
        );
        Map<String, Object> result = new HashMap<>();

        PageInfo pageInfo = PageInfo.builder()
                .currentPage(prescriptionList.getNumber() + 1)
                .totalPages(prescriptionList.getTotalPages())
                .pageSize(prescriptionList.getSize())
                .currentItems(prescriptionList.getNumberOfElements())
                .totalItems(prescriptionList.getTotalElements())
                .build();

        List<PrescriptionResponseDto> prescriptionDtoList = prescriptionList.stream()
                .map(PrescriptionResponseDto::toEntity)
                .toList();

        result.put("pageInfo", pageInfo);
        result.put("prescriptionList", prescriptionDtoList);

        return result;
    }

    /** 처방 안 받은 리스트 조회 */
    @Transactional
    public Map<String, List<PrescriptionResponseDto>> notReceivedPrescriptionList(Long userId) {
        List<Prescription> prescriptionList = prescriptionRepository.findByUserFkAndPreStFalse(userId);
        Map<String, List<PrescriptionResponseDto>> result = new HashMap<>();
        List<PrescriptionResponseDto> prescriptionDtoList = prescriptionList.stream()
                .map(PrescriptionResponseDto::toEntity)
                .toList();

        result.put("prescriptionList", prescriptionDtoList);

        return result;
    }

    // TODO: 수정요함 (용법(method) 해결 해야됨)
    /** 지식 + 1
     * 처방전 상세조회
     * @param prescriptionId  처방전 pk
     */
    @Transactional
    public Map<String, Object> prescriptionDetail(Long prescriptionId) {
        Prescription prescription = prescriptionRepository.findPrescriptionByPrescriptionPk(prescriptionId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRESCRIPTION));
        List<PrescriptionDisease> prescriptionDiseaseList = prescriptionDiseaseRepository.findByPreDisPrescription(prescription);
        List<PrescriptionInjection> prescriptionInjectionList = prescriptionInjectionRepository.findByPreInjPrescription(prescription);
        List<PrescriptionMedicine> prescriptionMedicineList = prescriptionMedicineRepository.findByPreMedPrescription(prescription);
        Map<String, Object> result = new HashMap<>();

        PrescriptionResponseDto prescriptionResponseDto = PrescriptionResponseDto.toEntity(prescription);
        List<DiseaseResponseDto> diseaseDtoList = prescriptionDiseaseList.stream()
                .map(prescriptionDisease -> DiseaseResponseDto.toEntity(prescriptionDisease.getPreDisDisease()))
                .toList();
        List<MedicineResponseDto> medicineDtoList = prescriptionMedicineList.stream()
                .map(prescriptionMedicine -> MedicineResponseDto.toEntity(prescriptionMedicine.getPreMedMedicine()))
                .toList();
        List<InjectionResponseDto> injectionDtoList = prescriptionInjectionList.stream()
                .map(prescriptionInjection -> InjectionResponseDto.toEntity(prescriptionInjection.getPreInjInjection()))
                .toList();

        result.put("prescription", prescriptionResponseDto);
        result.put("diseaseList", diseaseDtoList);
        result.put("medicineList", medicineDtoList);
        result.put("injectionList", injectionDtoList);
        result.put("user", UserResponseDto.toEntity(prescription.getPreUser()));
        result.put("doctor", DoctorResponseDto.toEntity(prescription.getPreDoctor()));
        result.put("hospital", HospitalResponseDto.toEntity(prescription.getPreDoctor().getDoctorHospital()));
        if (prescription.getPreChemist() != null) {
            result.put("chemist", ChemistResponseDto.toEntity(prescription.getPreChemist()));
            result.put("pharmacy", PharmacyResponseDto.toEntity(prescription.getPreChemist().getChemistPharmacy()));
        } else {
            result.put("chemist", null);
            result.put("pharmacy", null);
        }

        return result;
    }

    // 수정 요함
    /** 보험 청구 여부 수정 */
    @Transactional
    public Boolean updateInsuranceSt(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRESCRIPTION));

        prescription.updateInsuranceSt();
        return Boolean.TRUE;
    }

    /** 처방 여부 수정 */
    @Transactional
    public Boolean updatePrescriptionSt(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRESCRIPTION));

        prescription.updatePrescriptionSt();
        return Boolean.TRUE;
    }

//
//
//    @Transactional
//    public PrescriptionHtmlResponseDto getPrescriptionHtml(Long id) {
//        Prescription prescription = prescriptionRepository
//                .findById(id)
//                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
//
//        User user = prescription.getPreUser();
//
//        Doctor doctor = prescription.getPreDoctor();
//
//        Hospital hospital = doctor.getDoctorHospital();
//
//        List<Disease> disease = diseaseRepository.findByDiseasePrescription(prescription);
//
//        List<Medicine> medicine = prescriptionMedicineRepository
//                .findMedicineByPrescription(prescription);
//
//        List<MedicineDto> medicineList = new ArrayList<>();
//        List<String> diseaseList = new ArrayList<>();
//
//        medicine.forEach(e -> medicineList.add(MedicineDto.toEntity(e)));
//        disease.forEach(e -> diseaseList.add(e.getDiseaseCd()));
//
//
//        return PrescriptionHtmlResponseDto.toEntity(prescription, user, doctor, hospital, diseaseList, medicineList);
//    }
//




//    // 처방전 교부번호를 기준으로 조회 -> 처방전의 주키를 반환 (근데 처방전 교부 번호를 기준으로 조회하는 API를 만들면 되지 않나?
//    public QrResponse getPreQRByPreNo(Long userPk, int prescriptionNo) {
//        Prescription prescription = prescriptionRepository
//                .findByPrescriptionNo(prescriptionNo)
//                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));
//
//        return QrResponse.toEntity(prescription.getPrescriptionPk());
//    }


}
