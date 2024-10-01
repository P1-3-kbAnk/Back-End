package com.kbank.backend.repository;

import com.kbank.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    //처방전 create용 메서드 -김성헌
    List<User> findByUserNm(String userNm);
}
