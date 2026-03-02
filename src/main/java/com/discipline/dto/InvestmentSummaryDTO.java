package com.discipline.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class InvestmentSummaryDTO {
    private double totalInvested;
    private double totalCurrentValue;
    private double profit;
    private double profitPercentage;
    private List<AllocationItem> allocation;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class AllocationItem {
        private String type;
        private double value;
    }
}
