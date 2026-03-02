package com.discipline.repository;

import com.discipline.entity.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long> {

    List<CalendarEvent> findByStartTimeBetweenOrderByStartTimeAsc(LocalDateTime start, LocalDateTime end);
}
