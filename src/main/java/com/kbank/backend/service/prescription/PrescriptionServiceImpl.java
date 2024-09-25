package com.kbank.backend.service.prescription;


import com.kbank.backend.domain.Chemist;
import com.kbank.backend.domain.Doctor;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.User;

import com.kbank.backend.dto.request.PrescriptionRequest;
import com.kbank.backend.dto.response.PrescriptionResponse;
import com.kbank.backend.repository.ChemistRepository;
import com.kbank.backend.repository.DoctorRepository;
import com.kbank.backend.repository.PrescriptionRepository;
import com.kbank.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PrescriptionServiceImpl implements PrescriptionService{


    final private PrescriptionRepository repository;
    final private DoctorRepository doctorRepository;

    final private UserRepository userRepository;

    final private ChemistRepository chemistRepository;

    // id에 맞는 목록 하나 가져오기
    @Override
    public Optional<Prescription> findById(Long id) {
        return repository.findById(id);
    }

    //전체 리스트 가져오기
    @Override
    public List<Prescription> findAll() {
        return repository.findAll();
    }

    //조제여부가 false인 처방전 목록 조회하는 비지니스 로직
    @Override
    public List<Prescription> findNotRecived() {
        return repository.findByPrescriptionStFalse();
    }

    //
    @Override
    public PrescriptionResponse createPrescription(PrescriptionRequest prescriptionRequest) {
        Doctor doctor = doctorRepository.findById(prescriptionRequest.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + prescriptionRequest.getDoctorId()));
        User user = userRepository.findById(prescriptionRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + prescriptionRequest.getUserId()));
        Chemist chemist = chemistRepository.findById(prescriptionRequest.getChemistId())
                .orElseThrow(() -> new EntityNotFoundException("Chemist not found with id: " + prescriptionRequest.getChemistId()));
        // PrescriptionRequest에서 엔티티로 변환
        Prescription prescription = prescriptionRequest.toEntity(doctor, user, chemist);

        // DB에 저장
        Prescription savedPrescription = repository.save(prescription);

        // 저장된 엔티티를 DTO로 변환하여 반환
        return new PrescriptionResponse(savedPrescription);
    }


}
