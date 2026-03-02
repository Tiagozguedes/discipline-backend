package com.discipline.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FinanceSummaryDTO {
    private double totalIncome;
    private double totalExpense;
    private double balance;
    private List<CategoryAmount> expensesByCategory;
    private List<CategoryAmount> incomeByCategory;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CategoryAmount {
        private String category;
        private double amount;
    }
}
