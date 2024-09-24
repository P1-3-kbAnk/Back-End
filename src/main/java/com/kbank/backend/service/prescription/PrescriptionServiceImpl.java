package com.kbank.backend.service.prescription;


import com.kbank.backend.domain.Chemist;
import com.kbank.backend.domain.Doctor;
import com.kbank.backend.domain.Prescription;
import com.kbank.backend.domain.User;

import com.kbank.backend.dto.request.PrescriptionRequest;
import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.kbank.backend.repository.ChemistRepository;
import com.kbank.backend.repository.DoctorRepository;
import com.kbank.backend.repository.PrescriptionRepository;
import com.kbank.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

    @Override
    public Prescription createPrescription(PrescriptionRequest dto) {
        return null;
    }


}
