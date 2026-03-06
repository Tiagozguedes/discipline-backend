package com.discipline.service;

import com.discipline.dto.TaskCreateDTO;
import com.discipline.dto.TaskUpdateDTO;
import com.discipline.entity.Task;
import com.discipline.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAllByOrderByOrderIndexAsc();
    }

    public Task create(TaskCreateDTO dto) {
        Task task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus() != null ? dto.getStatus() : "todo")
                .priority(dto.getPriority() != null ? dto.getPriority() : "medium")
                .tag(dto.getTag())
                .tagColor(dto.getTagColor())
                .dueDate(dto.getDueDate() != null ? LocalDate.parse(dto.getDueDate()) : null)
                .build();
        return taskRepository.save(task);
    }

    public Task update(Long id, TaskUpdateDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));

        if (dto.getTitle() != null)
            task.setTitle(dto.getTitle());
        if (dto.getDescription() != null)
            task.setDescription(dto.getDescription());
        if (dto.getStatus() != null)
            task.setStatus(dto.getStatus());
        if (dto.getPriority() != null)
            task.setPriority(dto.getPriority());
        if (dto.getTag() != null)
            task.setTag(dto.getTag());
        if (dto.getTagColor() != null)
            task.setTagColor(dto.getTagColor());
        if (dto.getDueDate() != null)
            task.setDueDate(LocalDate.parse(dto.getDueDate()));
        if (dto.getOrderIndex() != null)
            task.setOrderIndex(dto.getOrderIndex());

        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public void reorder(List<TaskUpdateDTO> items) {
        for (TaskUpdateDTO item : items) {
            if (item.getId() == null)
                continue;
            Task task = taskRepository.findById(item.getId()).orElse(null);
            if (task == null)
                continue;
            if (item.getStatus() != null)
                task.setStatus(item.getStatus());
            if (item.getOrderIndex() != null)
                task.setOrderIndex(item.getOrderIndex());
            taskRepository.save(task);
        }
    }

    public long countAll() {
        return taskRepository.count();
    }

    public long countByStatus(String status) {
        return taskRepository.countByStatus(status);
    }
}
