package com.discipline.controller;

import com.discipline.dto.CalendarEventCreateDTO;
import com.discipline.entity.CalendarEvent;
import com.discipline.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping
    public List<CalendarEvent> findByRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return calendarService.findByDateRange(startDate, endDate);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CalendarEvent create(@RequestBody CalendarEventCreateDTO dto) {
        return calendarService.create(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        calendarService.delete(id);
    }
}
