package com.discipline.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class InvestmentCreateDTO {
    private String name;
    private String type;
    private Double amountInvested;
    private Double currentValue;
    private String purchaseDate;
    private String notes;
}
