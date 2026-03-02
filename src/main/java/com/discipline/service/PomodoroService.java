package com.discipline.service;

import com.discipline.dto.PomodoroCreateDTO;
import com.discipline.dto.PomodoroStatsDTO;
import com.discipline.entity.PomodoroSession;
import com.discipline.repository.PomodoroSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

@Service
@RequiredArgsConstructor
public class PomodoroService {

    private final PomodoroSessionRepository pomodoroRepository;

    public PomodoroSession create(PomodoroCreateDTO dto) {
        PomodoroSession session = PomodoroSession.builder()
                .durationMinutes(dto.getDurationMinutes())
                .startedAt(LocalDateTime.parse(dto.getStartedAt().replace("Z", "")))
                .endedAt(LocalDateTime.parse(dto.getEndedAt().replace("Z", "")))
                .completed(dto.getCompleted() != null ? dto.getCompleted() : false)
                .sessionType(dto.getSessionType() != null ? dto.getSessionType() : "focus")
                .build();
        return pomodoroRepository.save(session);
    }

    public PomodoroStatsDTO getStats(String period) {
        LocalDateTime start;
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);

        if ("week".equalsIgnoreCase(period)) {
            start = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).atStartOfDay();
        } else {
            start = LocalDate.now().atStartOfDay();
        }

        int totalMinutes = pomodoroRepository.sumMinutesByPeriod(start, end);
        int totalSessions = pomodoroRepository.countSessionsByPeriod(start, end);

        return PomodoroStatsDTO.builder()
                .totalMinutes(totalMinutes)
                .totalSessions(totalSessions)
                .period(period)
                .build();
    }

    public int getTodayMinutes() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);
        return pomodoroRepository.sumMinutesByPeriod(start, end);
    }

    public int getTodaySessions() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);
        return pomodoroRepository.countSessionsByPeriod(start, end);
    }

    public int getWeekMinutes() {
        LocalDateTime start = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).atStartOfDay();
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);
        return pomodoroRepository.sumMinutesByPeriod(start, end);
    }
}
