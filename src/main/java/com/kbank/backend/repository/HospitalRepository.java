package com.kbank.backend.repository;

import com.kbank.backend.domain.address.Dong;
import com.kbank.backend.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    // 특정 동(Dong)에 속한 병원 조회
    List<Hospital> findByHospitalDongFk(Dong dong);

    // 병원 이름으로 병원 조회
    List<Hospital> findByHospitalNm(String hospitalNm);

    // 전화번호로 병원 조회
    List<Hospital> findByPhoneNo(String phoneNo);
}