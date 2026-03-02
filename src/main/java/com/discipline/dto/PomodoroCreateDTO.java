package com.discipline.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PomodoroCreateDTO {
    private Integer durationMinutes;
    private String startedAt;
    private String endedAt;
    private Boolean completed;
    private String sessionType;
}
