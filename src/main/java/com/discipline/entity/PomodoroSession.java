package com.discipline.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pomodoro_sessions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PomodoroSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer durationMinutes;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column(nullable = false)
    private LocalDateTime endedAt;

    @Column(nullable = false)
    @Builder.Default
    private Boolean completed = false;

    @Column(nullable = false)
    private String sessionType; // focus, short_break, long_break
}
