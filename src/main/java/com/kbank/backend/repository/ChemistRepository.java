package com.kbank.backend.repository;

import com.kbank.backend.domain.Chemist;
import com.kbank.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChemistRepository extends JpaRepository<Chemist,Long> {

    @Query("SELECT c FROM Chemist c JOIN FETCH c.authUser au " +
            "WHERE au.authUserPk = :authUserId")
    Optional<Chemist> findByChemistWithAuthUserId(@Param("authUserId") Long authUserId);
}
