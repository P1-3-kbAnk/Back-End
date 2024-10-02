package com.kbank.backend.repository;

import com.kbank.backend.domain.Injection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InjectionRepository extends JpaRepository<Injection, Long> {

}
