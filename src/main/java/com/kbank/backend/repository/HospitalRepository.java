package com.kbank.backend.repository;

import com.kbank.backend.domain.address.Dong;
import com.kbank.backend.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    // 특정 ID로 병원 정보 조회
    Optional<Hospital> findById(Long id);

    // 모든 병원 정보 조회
    List<Hospital> findAll();

    // 특정 동(Dong)에 속한 병원 조회
    List<Hospital> findByHospitalDong(Dong dong);

    // 병원 이름으로 병원 조회
    List<Hospital> findByHospitalNm(String hospitalNm);

    // 전화번호로 병원 조회
    List<Hospital> findByPhoneNo(String phoneNo);
}