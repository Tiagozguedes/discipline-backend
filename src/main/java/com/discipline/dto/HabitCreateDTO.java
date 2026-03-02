package com.discipline.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class HabitCreateDTO {
    private String name;
    private String description;
    private String frequency;
    private String category;
    private String color;
}
