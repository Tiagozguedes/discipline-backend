package com.discipline.controller;

import com.discipline.dto.PomodoroCreateDTO;
import com.discipline.dto.PomodoroStatsDTO;
import com.discipline.entity.PomodoroSession;
import com.discipline.service.PomodoroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pomodoro")
@RequiredArgsConstructor
public class PomodoroController {

    private final PomodoroService pomodoroService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PomodoroSession create(@RequestBody PomodoroCreateDTO dto) {
        return pomodoroService.create(dto);
    }

    @GetMapping("/stats")
    public PomodoroStatsDTO getStats(@RequestParam(defaultValue = "today") String period) {
        return pomodoroService.getStats(period);
    }
}
