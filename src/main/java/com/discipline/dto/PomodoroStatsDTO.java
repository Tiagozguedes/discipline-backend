package com.discipline.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PomodoroStatsDTO {
    private int totalMinutes;
    private int totalSessions;
    private String period;
}
