package com.kbank.backend.service.prescription;

import com.kbank.backend.domain.*;
import com.kbank.backend.dto.response.*;
import com.kbank.backend.enumerate.Meal;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ChemistPrescriptionService {

    private final ChemistRepository chemistRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionInjectionRepository prescriptionInjectionRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;
    private final PrescriptionDiseaseRepository prescriptionDiseaseRepository;
    private final MedicineIntakeRepository medicineIntakeRepository;
    private final InjectionIntakeRepository injectionIntakeRepository;

    private void createAndSaveMedicineIntake(Medicine medicine, Meal meal, User user, LocalDate day, Integer intakeCnt) {
        MedicineIntake newMedicineIntake = MedicineIntake.builder()
                .medInkMedicine(medicine)
                .meal(meal)
                .day(day)
                .intakeCnt(intakeCnt)
                .medInkUser(user)
                .build();
        medicineIntakeRepository.save(newMedicineIntake);
    }

    private void createAndSaveInjectionIntake(Injection injection, Meal meal, User user, LocalDate day, Integer intakeCnt) {
        InjectionIntake newInjectionIntake = InjectionIntake.builder()
                .injInkInjection(injection)
                .meal(meal)
                .day(day)
                .intakeCnt(intakeCnt)
                .injInkUser(user)
                .build();
        injectionIntakeRepository.save(newInjectionIntake);
    }

    public Boolean updatePrescriptionSt(Long chemistId, Long id) {
        Chemist chemist = chemistRepository.findByChemistWithAuthUserId(chemistId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_CHEMIST));
        Prescription prescription = prescriptionRepository.findByPrescriptionPk(id)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRESCRIPTION));

        if (prescription.getPrescriptionSt() == Boolean.TRUE) {
            return false;
        } if (prescription.getCreateYmd().toLocalDate().plusDays(prescription.getDuration()).equals(LocalDate.now())) {
            throw new CommonException(ErrorCode.INVALID_PRESCRIPTION);
        }

        List<PrescriptionMedicine> prescriptionMedicineList = prescriptionMedicineRepository.findByPreMedPrescription(prescription);
        List<PrescriptionInjection> prescriptionInjectionList = prescriptionInjectionRepository.findByPreInjPrescription(prescription);
        LocalDate day = LocalDate.now();

        for (PrescriptionMedicine pm : prescriptionMedicineList) {
            for (int i = 0; i < pm.getTotalDay(); i++) {
                if (pm.getDosePerMorning() != 0) {
                    createAndSaveMedicineIntake(
                            pm.getPreMedMedicine(),
                            Meal.BREAKFAST,
                            prescription.getPreUser(),
                            day.plusDays(i),
                            pm.getDosePerMorning()
                    );
                } if (pm.getDosePerLunch() != 0) {
                    createAndSaveMedicineIntake(
                            pm.getPreMedMedicine(),
                            Meal.LUNCH,
                            prescription.getPreUser(),
                            day.plusDays(i),
                            pm.getDosePerLunch()
                    );
                } if (pm.getDosePerDinner() != 0) {
                    createAndSaveMedicineIntake(
                            pm.getPreMedMedicine(),
                            Meal.DINNER,
                            prescription.getPreUser(),
                            day.plusDays(i),
                            pm.getDosePerDinner()
                    );
                }
            }
        }

        for (PrescriptionInjection pi : prescriptionInjectionList) {
            for (int i = 0; i < pi.getTotalDay(); i++) {
                if (pi.getDosePerMorning() != 0) {
                    createAndSaveInjectionIntake(
                            pi.getPreInjInjection(),
                            Meal.BREAKFAST,
                            prescription.getPreUser(),
                            day.plusDays(i),
                            pi.getDosePerMorning()
                    );
                } if (pi.getDosePerLunch() != 0) {
                    createAndSaveInjectionIntake(
                            pi.getPreInjInjection(),
                            Meal.LUNCH,
                            prescription.getPreUser(),
                            day.plusDays(i),
                            pi.getDosePerLunch()
                    );
                } if (pi.getDosePerDinner() != 0) {
                    createAndSaveInjectionIntake(
                            pi.getPreInjInjection(),
                            Meal.DINNER,
                            prescription.getPreUser(),
                            day.plusDays(i),
                            pi.getDosePerDinner()
                    );
                }
            }
        }

        prescription.updatePrescriptionSt(chemist);
        return Boolean.TRUE;
    }

    /** 전체 리스트 조회 */
    public Map<String, Object> getAllPrescriptionList(Long userId, Integer pageIndex, Integer pageSize) {

        Chemist chemist = chemistRepository.findByChemistWithAuthUserId(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_CHEMIST));

        Page<Prescription> prescriptionList = prescriptionRepository.findAllByPreChemist(
                chemist,
                PageRequest.of(pageIndex, pageSize, Sort.by(
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

}
