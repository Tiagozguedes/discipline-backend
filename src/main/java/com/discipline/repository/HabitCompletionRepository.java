package com.discipline.repository;

import com.discipline.entity.HabitCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Long> {

    Optional<HabitCompletion> findByHabitIdAndCompletedDate(Long habitId, LocalDate completedDate);

    long countByCompletedDate(LocalDate date);
}
