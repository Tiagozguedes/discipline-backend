package com.discipline.service;

import com.discipline.dto.HabitCreateDTO;
import com.discipline.dto.HabitUpdateDTO;
import com.discipline.entity.Habit;
import com.discipline.entity.HabitCompletion;
import com.discipline.repository.HabitCompletionRepository;
import com.discipline.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final HabitCompletionRepository habitCompletionRepository;

    public List<Habit> findAllActive() {
        return habitRepository.findByActiveTrueOrderByIdAsc();
    }

    public Habit create(HabitCreateDTO dto) {
        Habit habit = Habit.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .frequency(dto.getFrequency() != null ? dto.getFrequency() : "daily")
                .category(dto.getCategory() != null ? dto.getCategory() : "general")
                .color(dto.getColor())
                .build();
        return habitRepository.save(habit);
    }

    public Habit update(Long id, HabitUpdateDTO dto) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found: " + id));

        if (dto.getName() != null)
            habit.setName(dto.getName());
        if (dto.getDescription() != null)
            habit.setDescription(dto.getDescription());
        if (dto.getFrequency() != null)
            habit.setFrequency(dto.getFrequency());
        if (dto.getCategory() != null)
            habit.setCategory(dto.getCategory());
        if (dto.getColor() != null)
            habit.setColor(dto.getColor());
        if (dto.getActive() != null)
            habit.setActive(dto.getActive());

        return habitRepository.save(habit);
    }

    public void delete(Long id) {
        habitRepository.deleteById(id);
    }

    @Transactional
    public void toggleCompletion(Long habitId, String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found: " + habitId));

        Optional<HabitCompletion> existing = habitCompletionRepository.findByHabitIdAndCompletedDate(habitId, date);

        if (existing.isPresent()) {
            habitCompletionRepository.delete(existing.get());
        } else {
            HabitCompletion completion = HabitCompletion.builder()
                    .habit(habit)
                    .completedDate(date)
                    .build();
            habitCompletionRepository.save(completion);
        }
    }

    public long countActive() {
        return habitRepository.countByActiveTrue();
    }

    public long countCompletedToday() {
        return habitCompletionRepository.countByCompletedDate(LocalDate.now());
    }
}
