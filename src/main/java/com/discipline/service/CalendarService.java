package com.discipline.service;

import com.discipline.dto.CalendarEventCreateDTO;
import com.discipline.dto.CalendarEventUpdateDTO;
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

    public CalendarEvent update(Long id, CalendarEventUpdateDTO dto) {
        CalendarEvent event = calendarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calendar event not found: " + id));

        if (dto.getTitle() != null)
            event.setTitle(dto.getTitle());
        if (dto.getDescription() != null)
            event.setDescription(dto.getDescription());
        if (dto.getStartTime() != null)
            event.setStartTime(LocalDateTime.parse(dto.getStartTime()));
        if (dto.getEndTime() != null)
            event.setEndTime(LocalDateTime.parse(dto.getEndTime()));
        if (dto.getColor() != null)
            event.setColor(dto.getColor());
        if (dto.getAllDay() != null)
            event.setAllDay(dto.getAllDay());

        return calendarRepository.save(event);
    }

    public void delete(Long id) {
        calendarRepository.deleteById(id);
    }
}
