package com.kbank.backend.repository;


import com.kbank.backend.domain.Injection;
import com.kbank.backend.domain.InjectionIntake;
import com.kbank.backend.domain.PrescriptionInjection;
import com.kbank.backend.domain.User;
import com.kbank.backend.enumerate.Meal;
import org.springframework.cglib.core.Local;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InjectionIntakeRepository extends JpaRepository<InjectionIntake, Long> {
    List<InjectionIntake> findByInjInkUser(User user);
    //List<InjectionIntake> findByUserAndMeal(User user, Meal meal);
    //List<InjectionIntake> findByUserAndEatSt(User user, boolean eatSt);
    List<InjectionIntake> findByInjInkUserAndDay(User user, LocalDate date);

//    @Query("SELECT i FROM InjectionIntake ii JOIN FETCH Injection i on ii.injection.id = i.id WHERE ii.id = :pk")
//    Optional<Injection> findInjectionById(@Param("pk") long id);
}
