package com.kbank.backend.repository;

import com.kbank.backend.domain.User;
import com.kbank.backend.enumerate.Provider;
import com.kbank.backend.enumerate.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserPk(Long userPk);

    Optional<User> findById(Long id);

}
