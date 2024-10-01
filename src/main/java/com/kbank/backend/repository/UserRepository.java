package com.kbank.backend.repository;

import com.kbank.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserPk(Long userPk);

    Optional<User> findById(Long id);
    //처방전 create용 메서드 -김성헌
    List<User> findByUserNm(String userNm);
}
