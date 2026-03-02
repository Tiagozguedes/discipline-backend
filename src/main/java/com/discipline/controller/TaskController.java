package com.discipline.controller;

import com.discipline.dto.TaskCreateDTO;
import com.discipline.dto.TaskUpdateDTO;
import com.discipline.entity.Task;
import com.discipline.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody TaskCreateDTO dto) {
        return taskService.create(dto);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody TaskUpdateDTO dto) {
        return taskService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
