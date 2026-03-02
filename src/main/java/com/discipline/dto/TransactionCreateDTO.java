package com.discipline.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TransactionCreateDTO {
    private String description;
    private Double amount;
    private String type;
    private String category;
    private String date;
}
