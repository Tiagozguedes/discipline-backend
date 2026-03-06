package com.discipline.service;

import com.discipline.dto.SubTaskCreateDTO;
import com.discipline.entity.SubTask;
import com.discipline.entity.Task;
import com.discipline.repository.SubTaskRepository;
import com.discipline.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubTaskService {

    private final SubTaskRepository subTaskRepository;
    private final TaskRepository taskRepository;

    public List<SubTask> findByTaskId(Long taskId) {
        return subTaskRepository.findByTaskIdOrderByIdAsc(taskId);
    }

    public SubTask create(Long taskId, SubTaskCreateDTO dto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found: " + taskId));

        SubTask subTask = SubTask.builder()
                .title(dto.getTitle())
                .completed(false)
                .task(task)
                .build();
        return subTaskRepository.save(subTask);
    }

    @Transactional
    public SubTask toggle(Long subtaskId) {
        SubTask subTask = subTaskRepository.findById(subtaskId)
                .orElseThrow(() -> new RuntimeException("SubTask not found: " + subtaskId));
        subTask.setCompleted(!subTask.getCompleted());
        return subTaskRepository.save(subTask);
    }

    public void delete(Long subtaskId) {
        subTaskRepository.deleteById(subtaskId);
    }
}
