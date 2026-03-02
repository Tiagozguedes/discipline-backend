package com.discipline.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DashboardDTO {
    private TasksSummary tasks;
    private PomodoroSummary pomodoro;
    private HabitsSummary habits;
    private FinanceSummaryShort finance;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class TasksSummary {
        private long total;
        private long todo;
        private long inProgress;
        private long done;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class PomodoroSummary {
        private int todayMinutes;
        private int todaySessions;
        private int weekMinutes;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class HabitsSummary {
        private long totalActive;
        private long completedToday;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class FinanceSummaryShort {
        private double monthIncome;
        private double monthExpense;
        private double monthBalance;
        private double totalInvested;
        private double totalInvestmentValue;
    }
}
