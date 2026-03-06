package com.discipline.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CalendarEventUpdateDTO {
    private String title;
    private String description;
    private String startTime;
    private String endTime;
    private String color;
    private Boolean allDay;
}
