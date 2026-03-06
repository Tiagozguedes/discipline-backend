package com.discipline.controller;

import com.discipline.dto.SubTaskCreateDTO;
import com.discipline.dto.TaskCreateDTO;
import com.discipline.dto.TaskUpdateDTO;
import com.discipline.entity.SubTask;
import com.discipline.entity.Task;
import com.discipline.service.SubTaskService;
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
    private final SubTaskService subTaskService;

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

    @PutMapping("/reorder")
    public void reorder(@RequestBody List<TaskUpdateDTO> items) {
        taskService.reorder(items);
    }

    // ---- SubTasks ----

    @PostMapping("/{taskId}/subtasks")
    @ResponseStatus(HttpStatus.CREATED)
    public SubTask createSubTask(@PathVariable Long taskId, @RequestBody SubTaskCreateDTO dto) {
        return subTaskService.create(taskId, dto);
    }

    @PutMapping("/{taskId}/subtasks/{subtaskId}/toggle")
    public SubTask toggleSubTask(@PathVariable Long taskId, @PathVariable Long subtaskId) {
        return subTaskService.toggle(subtaskId);
    }

    @DeleteMapping("/{taskId}/subtasks/{subtaskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubTask(@PathVariable Long taskId, @PathVariable Long subtaskId) {
        subTaskService.delete(subtaskId);
    }
}
