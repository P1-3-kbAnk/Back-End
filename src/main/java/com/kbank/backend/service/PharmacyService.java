package com.kbank.backend.service;

import com.kbank.backend.domain.Chemist;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.dto.response.DiseaseResponseDto;
import com.kbank.backend.dto.response.PageInfo;
import com.kbank.backend.dto.response.PrescriptionSummarizeDto;
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
public class PharmacyService {
    private final PrescriptionRepository prescriptionRepository;
    private final DiseaseRepository diseaseRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;
    private final PrescriptionDiseaseRepository prescriptionDiseaseRepository;
    private final ChemistRepository chemistRepository;


    //처방 완료 -> 약국 id랑 같은 것 중에서
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

}
