package com.kbank.backend.service.prescription;

import com.kbank.backend.domain.*;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;
    private final PrescriptionInjectionRepository prescriptionInjectionRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;
    private final PrescriptionDiseaseRepository prescriptionDiseaseRepository;


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

    // TODO
    public Boolean setPrescriptionSt(Long id){
        Prescription prescription=prescriptionRepository.findById(id)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PRESCRIPTION));

        prescription.setPrescriptionSt();

        return Boolean.TRUE;
    }
}
