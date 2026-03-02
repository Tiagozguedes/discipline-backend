package com.discipline.repository;

import com.discipline.entity.PomodoroSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PomodoroSessionRepository extends JpaRepository<PomodoroSession, Long> {

    List<PomodoroSession> findByCompletedTrueAndSessionTypeAndStartedAtBetween(
            String sessionType, LocalDateTime start, LocalDateTime end);

    @Query("SELECT COALESCE(SUM(p.durationMinutes), 0) FROM PomodoroSession p " +
           "WHERE p.completed = true AND p.sessionType = 'focus' AND p.startedAt BETWEEN :start AND :end")
    Integer sumMinutesByPeriod(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(p) FROM PomodoroSession p " +
           "WHERE p.completed = true AND p.sessionType = 'focus' AND p.startedAt BETWEEN :start AND :end")
    Integer countSessionsByPeriod(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
