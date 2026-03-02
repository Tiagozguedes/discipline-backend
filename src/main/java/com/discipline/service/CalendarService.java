package com.discipline.service;

import com.discipline.dto.CalendarEventCreateDTO;
import com.discipline.entity.CalendarEvent;
import com.discipline.repository.CalendarEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarEventRepository calendarRepository;

    public List<CalendarEvent> findByDateRange(String startDate, String endDate) {
        LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endDate).atTime(LocalTime.MAX);
        return calendarRepository.findByStartTimeBetweenOrderByStartTimeAsc(start, end);
    }

    public CalendarEvent create(CalendarEventCreateDTO dto) {
        CalendarEvent event = CalendarEvent.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .startTime(LocalDateTime.parse(dto.getStartTime()))
                .endTime(LocalDateTime.parse(dto.getEndTime()))
                .color(dto.getColor() != null ? dto.getColor() : "#FABE00")
                .allDay(dto.getAllDay() != null ? dto.getAllDay() : false)
                .build();
        return calendarRepository.save(event);
    }

    public void delete(Long id) {
        calendarRepository.deleteById(id);
    }
}
