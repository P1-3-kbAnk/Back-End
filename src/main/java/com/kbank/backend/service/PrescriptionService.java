package com.kbank.backend.service;

import com.kbank.backend.domain.*;
import com.kbank.backend.dto.request.InjectionIntakeInfoRequestDto;
import com.kbank.backend.dto.request.MedicineIntakeInfoRequestDto;
import com.kbank.backend.dto.request.PrescriptionRequestDto;
import com.kbank.backend.dto.response.*;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
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


    /**
     * 처방전 생성 &  병원 영수증 생성
     */
    public Long createPrescription(Long doctorId, PrescriptionRequestDto prescriptionRequestDto) {
        Doctor doctor = doctorRepository.findByIdWithHospital(doctorId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_DOCTOR));
        User user = userRepository.findUserByUserNmAndFirstNoAndLastNo(
                    prescriptionRequestDto.getUserNm(),
                    prescriptionRequestDto.getFirstNo(),
                    prescriptionRequestDto.getLastNo()
                )
                .orElseThrow(() ->new CommonException(ErrorCode.NOT_FOUND_USER));
        Integer prescriptionNo = prescriptionRepository.findAllByDate(LocalDate.now());

        // Prescription 엔티티 생성 및 저장
        Prescription prescription = Prescription.builder()
                .preDoctor(doctor)
                .preUser(user)
                .preChemist(null)
                .prescriptionNo(prescriptionNo + 1)
                .duration(3)
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
        if ((prescriptionRequestDto.getInjectionIntakeInfoList() == null || prescriptionRequestDto.getInjectionIntakeInfoList().isEmpty())
                && (prescriptionRequestDto.getMedicineIntakeInfoList() == null || prescriptionRequestDto.getMedicineIntakeInfoList().isEmpty())) {
            throw new CommonException(ErrorCode.MISSING_REQUEST_PARAMETER);
        }

        int maxDate = 0;

        // 약 정보 저장
        if (prescriptionRequestDto.getMedicineIntakeInfoList() != null && !prescriptionRequestDto.getMedicineIntakeInfoList().isEmpty()) {
            List<PrescriptionMedicine> prescriptionMedicineList = new ArrayList<>();

            for (MedicineIntakeInfoRequestDto medicineInfo : prescriptionRequestDto.getMedicineIntakeInfoList()) {
                Medicine medicine = medicineRepository.findById(medicineInfo.getMedicinePk())
                        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MEDICINE));

                Integer cnt= 0;
                if (medicineInfo.getDosePerMorning() > 0) {
                    cnt++;
                } if (medicineInfo.getDosePerLunch() > 0) {
                    cnt++;
                } if (medicineInfo.getDosePerDinner() > 0) {
                    cnt++;
                }

                PrescriptionMedicine newPrescriptionMedicine = PrescriptionMedicine.builder()
                        .medicineNm(medicine.getMedicineNm())
                        .dosePerMorning(medicineInfo.getDosePerMorning())
                        .dosePerLunch(medicineInfo.getDosePerLunch())
                        .dosePerDinner(medicineInfo.getDosePerDinner())
                        .method(medicineInfo.getMethod())
                        .dayCnt(cnt)
                        .totalDay(medicineInfo.getTotalDay())
                        .preMedMedicine(medicine)
                        .preMedPrescription(prescription)
                        .build();

                maxDate = (maxDate > medicineInfo.getTotalDay()) ? maxDate : medicineInfo.getTotalDay();
                prescriptionMedicineList.add(newPrescriptionMedicine);
            }

            prescriptionMedicineRepository.saveAll(prescriptionMedicineList);
        }

        // 주사제 저장
        if (prescriptionRequestDto.getInjectionIntakeInfoList() != null && !prescriptionRequestDto.getInjectionIntakeInfoList().isEmpty()) {
            List<PrescriptionInjection> prescriptionInjectionList = new ArrayList<>();

            for (InjectionIntakeInfoRequestDto injectionInfo : prescriptionRequestDto.getInjectionIntakeInfoList()) {
                Injection injection = injectionRepository.findById(injectionInfo.getInjectionPk())
                        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MEDICINE));

                Integer cnt= 0;
                if (injectionInfo.getDosePerMorning() > 0) {
                    cnt++;
                } if (injectionInfo.getDosePerLunch() > 0) {
                    cnt++;
                } if (injectionInfo.getDosePerDinner() > 0) {
                    cnt++;
                }

                PrescriptionInjection newPrescriptionInjection = PrescriptionInjection.builder()
                        .injectionNm(injection.getInjectionNm())
                        .dosePerMorning(injectionInfo.getDosePerMorning())
                        .dosePerLunch(injectionInfo.getDosePerLunch())
                        .dosePerDinner(injectionInfo.getDosePerDinner())
                        .dayCnt(cnt)
                        .method(injectionInfo.getMethod())
                        .totalDay(injectionInfo.getTotalDay())
                        .preInjInjection(injection)
                        .preInjPrescription(prescription)
                        .build();

                maxDate = (maxDate > injectionInfo.getTotalDay()) ? maxDate : injectionInfo.getTotalDay();
                prescriptionInjectionList.add(newPrescriptionInjection);
            }

            prescriptionInjectionRepository.saveAll(prescriptionInjectionList);
        }

        prescription.updatePrescriptionMaxDate(maxDate);

        // 병원 영수증 저장
        HospitalBill newHospitalBill = HospitalBill.builder()
                .hospitalBillHospital(doctor.getDoctorHospital())
                .hospitalBillPrescription(prescription)
                .totalPrice(8600L)
                .build();

        hospitalBillRepository.save(newHospitalBill);

        return prescription.getPrescriptionPk();
    }

    /** 전체 리스트 조회 */
    public Map<String, Object> getAllPrescriptionList(Long userId, Integer pageIndex, Integer pageSize) {

        User user = userRepository.findByUserWithAuthUserId(userId)
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

        List<PrescriptionSummarizeDto> prescriptionDtoList = prescriptionList.stream()
                .map(prescription -> {
                      List<DiseaseResponseDto> diseaseList = prescriptionDiseaseRepository.findByPreDisPrescription(prescription)
                            .stream()
                            .map(prescriptionDisease -> DiseaseResponseDto.fromEntity(prescriptionDisease.getPreDisDisease()))
                            .toList();

                    if (prescription.getPreChemist() == null) {
                        return PrescriptionSummarizeDto.fromEntity(prescription, diseaseList);
                    } else {
                        return PrescriptionSummarizeDto.fromEntityWithPharmacy(prescription, diseaseList);
                    }

                }).toList();

        result.put("pageInfo", pageInfo);
        result.put("prescriptionList", prescriptionDtoList);

        return result;
    }

    /** 처방 안 받은 리스트 조회 */
    public Map<String, List<PrescriptionSummarizeDto>> notReceivedPrescriptionList(Long userId) {
        List<Prescription> prescriptionList = prescriptionRepository.findByUserFkAndPreStFalse(userId);
        Map<String, List<PrescriptionSummarizeDto>> result = new HashMap<>();
        List<PrescriptionSummarizeDto> prescriptionDtoList = prescriptionList.stream()
                .map(prescription -> {
                    List<DiseaseResponseDto> diseaseList = prescriptionDiseaseRepository.findByPreDisPrescription(prescription)
                            .stream()
                            .map(prescriptionDisease -> DiseaseResponseDto.fromEntity(prescriptionDisease.getPreDisDisease()))
                            .toList();
                    return PrescriptionSummarizeDto.fromEntity(prescription, diseaseList);
                }).toList();

        result.put("prescriptionList", prescriptionDtoList);

        return result;
    }


    /** 지식 + 1
     * 처방전 상세조회
     * 대대적 수정 요함
     * @param prescriptionId  처방전 pk
     */
    public Map<String, Object> prescriptionDetail(Long prescriptionId) {
        Prescription prescription = prescriptionRepository.findPrescriptionByPrescriptionPk(prescriptionId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRESCRIPTION));
        List<PrescriptionDisease> prescriptionDiseaseList = prescriptionDiseaseRepository.findByPreDisPrescription(prescription);
        List<PrescriptionInjection> prescriptionInjectionList = prescriptionInjectionRepository.findByPreInjPrescription(prescription);
        List<PrescriptionMedicine> prescriptionMedicineList = prescriptionMedicineRepository.findByPreMedPrescription(prescription);
        Map<String, Object> result = new HashMap<>();

        PrescriptionResponseDto prescriptionResponseDto = PrescriptionResponseDto.fromEntity(prescription);
        List<DiseaseResponseDto> diseaseDtoList = prescriptionDiseaseList.stream()
                .map(prescriptionDisease -> DiseaseResponseDto.fromEntity(prescriptionDisease.getPreDisDisease()))
                .toList();
        List<PrescriptionMedicineResponseDto> prescriptionMedicineDtoList = prescriptionMedicineList.stream()
                .map(PrescriptionMedicineResponseDto::fromEntity)  // PrescriptionMedicine -> PrescriptionMedicineResponseDto
                .toList();
        List<PrescriptionInjectionResponseDto> prescriptionInjectionDtoList=prescriptionInjectionList.stream()
                .map(PrescriptionInjectionResponseDto::fromEntity)
                .toList();

        result.put("prescription", prescriptionResponseDto);
        result.put("diseaseList", diseaseDtoList);
        result.put("preMedicineList", prescriptionMedicineDtoList);
        result.put("preInjectionList", prescriptionInjectionDtoList);
        result.put("user", UserResponseDto.fromEntity(prescription.getPreUser()));
        result.put("doctor", DoctorResponseDto.fromEntity(prescription.getPreDoctor()));
        result.put("hospital", HospitalResponseDto.fromEntity(prescription.getPreDoctor().getDoctorHospital()));
        if (prescription.getPreChemist() != null) {
            result.put("chemist", ChemistResponseDto.fromEntity(prescription.getPreChemist()));
            result.put("pharmacy", PharmacyResponseDto.fromEntity(prescription.getPreChemist().getChemistPharmacy()));
        } else {
            result.put("chemist", null);
            result.put("pharmacy", null);
        }

        return result;
    }

    /** 보험 청구 여부 수정 */
    public Boolean updateInsuranceSt(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRESCRIPTION));

        prescription.updateInsuranceSt();

        return Boolean.TRUE;
    }
    public Boolean setPrescriptionSt(Long id){
        Prescription prescription=prescriptionRepository.findById(id)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRESCRIPTION));

        prescription.setPrescriptionSt();

        return Boolean.TRUE;
    }
}
