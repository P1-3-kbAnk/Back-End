package com.kbank.backend.service;

import com.kbank.backend.domain.*;
import com.kbank.backend.dto.request.ChemistRequestDto;
import com.kbank.backend.enumerate.Meal;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ChemistService {

    private final PharmacyRepository pharmacyRepository;
    private final ChemistRepository chemistRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicineIntakeRepository medicineIntakeRepository;
    private final InjectionIntakeRepository injectionIntakeRepository;
    private final PrescriptionInjectionRepository prescriptionInjectionRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;

    public Boolean createChemist(ChemistRequestDto chemistRequestDto) {

        // 시큐리티 후 수정
        Pharmacy pharmacy = pharmacyRepository.findByPharmacyPk(chemistRequestDto.getPharmacyPk())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PHARMACY));
        Chemist newChemist = Chemist.builder()
                .chemistNm(chemistRequestDto.getChemistNm())
                .chemistNo(chemistRequestDto.getChemistNo())
                .phoneNo(chemistRequestDto.getPhoneNo())
                .gender(chemistRequestDto.getGender())
                .chemistPharmacy(pharmacy)
                .build();

        chemistRepository.save(newChemist);

        return Boolean.TRUE;
    }

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

    /** 처방 여부 수정 */
    public Boolean updatePrescriptionSt(Long chemistId, Long id) {
        Chemist chemist = chemistRepository.findById(chemistId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_CHEMIST));
        Prescription prescription = prescriptionRepository.findByPrescriptionPk(id)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRESCRIPTION));

        if (prescription.getPrescriptionSt() == Boolean.FALSE) {
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
        prescription.updatePrescribeYmd();

        return Boolean.TRUE;
    }
}
