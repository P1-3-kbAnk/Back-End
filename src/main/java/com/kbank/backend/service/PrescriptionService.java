package com.kbank.backend.service;

import com.kbank.backend.domain.*;
import com.kbank.backend.dto.DiseaseDto;
import com.kbank.backend.dto.request.DiseaseRequestDto;
import com.kbank.backend.dto.request.PrescriptionMedicineRequestDto;
import com.kbank.backend.dto.request.PrescriptionRequestDto;
import com.kbank.backend.dto.response.PrescriptionMedicineResponseDto;
import com.kbank.backend.dto.response.PrescriptionResponseDto;
import com.kbank.backend.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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


    @Transactional
    public boolean createPrescription(PrescriptionRequestDto prescriptionRequestDto) {
        try {
            // Doctor, User, Chemist 엔티티 조회
            Doctor doctor = doctorRepository.findById(prescriptionRequestDto.getDoctorId())
                    .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + prescriptionRequestDto.getDoctorId()));
            User user = userRepository.findById(prescriptionRequestDto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + prescriptionRequestDto.getUserId()));
            Chemist chemist = chemistRepository.findById(prescriptionRequestDto.getChemistId())
                    .orElseThrow(() -> new EntityNotFoundException("Chemist not found with id: " + prescriptionRequestDto.getChemistId()));

            // Prescription 엔티티 빌더 사용
            Prescription prescription = Prescription.builder()
                    .preDoctor(doctor)
                    .preUser(user)
                    .preChemist(chemist)
                    .prescriptionNo(prescriptionRequestDto.getPrescriptionNo())
                    .duration(prescriptionRequestDto.getDuration())
                    .description(prescriptionRequestDto.getDescription())
                    .prescriptionSt(prescriptionRequestDto.isPrescriptionSt())
                    .insuranceSt(prescriptionRequestDto.isInsuranceSt())
                    .build();

            // DB에 저장
            prescriptionRepository.save(prescription);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 특정 id로 처방전 조회
    public Optional<Prescription> findById(Long id) {
        return prescriptionRepository.findById(id);
    }

    // 모든 처방전 조회
    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    // 조제 여부가 false인 처방전 목록 조회
    public List<Prescription> findNotReceived() {
        return prescriptionRepository.findByPrescriptionStFalse();
    }

    //질병과 같이 조회하기
    public PrescriptionResponseDto getPrescriptionWithDisease(Long prescriptionId) {
        // Prescription 엔티티 조회
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        // Disease 엔티티를 Prescription 객체로 조회
        List<Disease> diseases = diseaseRepository.findByDiseasePrescription(prescription);

        // Disease 엔티티를 DTO로 변환
        List<DiseaseRequestDto> diseaseDTOs = diseases.stream()
                .map(disease -> DiseaseRequestDto.builder()
                        .diseaseCd(disease.getDiseaseCd())
                        .build())
                .collect(Collectors.toList());

//        //premed 엔티티 조회
//        List<PrescriptionMedicine> prescriptionMedicines = prescriptionMedicineRepository.findByPreMedPrescription(prescription);
//
//        List<PrescriptionMedicineRequestDto> preMedReqDtos=prescriptionMedicines.stream()
//                .map(prescriptionMedicine -> PrescriptionMedicineRequestDto.builder()
//
//                        .build())


        // PrescriptionResponseDto로 변환 및 반환
        return PrescriptionResponseDto.toEntity(prescription, diseaseDTOs);
    }

    @Transactional
    public PrescriptionResponseDto createPrescriptionWithDiseases(PrescriptionRequestDto requestDto) {
        // 1. 필요한 객체를 조회
        Doctor doctor = doctorRepository.findById(requestDto.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + requestDto.getDoctorId()));
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + requestDto.getUserId()));
        Chemist chemist = chemistRepository.findById(requestDto.getChemistId())
                .orElseThrow(() -> new EntityNotFoundException("Chemist not found with id: " + requestDto.getChemistId()));


        // 2. Prescription 엔티티 생성
        Prescription prescription = Prescription.builder()
                .preDoctor(doctor)  // 객체 참조
                .preUser(user)      // 객체 참조
                .preChemist(chemist) // 객체 참조
                .prescriptionNo(requestDto.getPrescriptionNo())
                .duration(requestDto.getDuration())
                .description(requestDto.getDescription())
                .prescriptionSt(requestDto.isPrescriptionSt())
                .insuranceSt(requestDto.isInsuranceSt())
                .build();

        // 3. 처방전 저장
        prescriptionRepository.save(prescription);

        // 4. 관련된 질병 정보 저장
        List<Disease> diseases = requestDto.getDiseases().stream()
                .map(diseaseDto -> Disease.builder()
                        .diseaseCd(diseaseDto.getDiseaseCd())
                        .diseasePrescription(prescription)  // 처방전과 연결
                        .build())
                .collect(Collectors.toList());

        diseaseRepository.saveAll(diseases);



        // 5. DTO로 변환하여 반환
        List<DiseaseRequestDto> diseaseDTOs = diseases.stream()
                .map(disease -> DiseaseRequestDto.builder().diseaseCd(disease.getDiseaseCd()).build())
                .collect(Collectors.toList());

        return PrescriptionResponseDto.toEntity(prescription, diseaseDTOs);
    }

}
