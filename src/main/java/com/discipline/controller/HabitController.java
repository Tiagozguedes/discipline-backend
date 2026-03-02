package com.discipline.controller;

import com.discipline.dto.HabitCreateDTO;
import com.discipline.entity.Habit;
import com.discipline.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping
    public List<Habit> findAll() {
        return habitService.findAllActive();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Habit create(@RequestBody HabitCreateDTO dto) {
        return habitService.create(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        habitService.delete(id);
    }

    @PostMapping("/{id}/toggle")
    public void toggle(@PathVariable Long id, @RequestParam String date) {
        habitService.toggleCompletion(id, date);
    }
}
