package com.discipline.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TaskCreateDTO {
    private String title;
    private String description;
    private String status;
    private String priority;
    private String tag;
    private String tagColor;
    private String dueDate;
}
