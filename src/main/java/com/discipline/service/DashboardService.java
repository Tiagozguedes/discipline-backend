package com.discipline.service;

import com.discipline.dto.DashboardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TaskService taskService;
    private final PomodoroService pomodoroService;
    private final HabitService habitService;
    private final FinanceService financeService;

    public DashboardDTO getDashboard() {
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();

        DashboardDTO.TasksSummary tasks = DashboardDTO.TasksSummary.builder()
                .total(taskService.countAll())
                .todo(taskService.countByStatus("todo"))
                .inProgress(taskService.countByStatus("in_progress"))
                .done(taskService.countByStatus("done"))
                .build();

        DashboardDTO.PomodoroSummary pomodoro = DashboardDTO.PomodoroSummary.builder()
                .todayMinutes(pomodoroService.getTodayMinutes())
                .todaySessions(pomodoroService.getTodaySessions())
                .weekMinutes(pomodoroService.getWeekMinutes())
                .build();

        DashboardDTO.HabitsSummary habits = DashboardDTO.HabitsSummary.builder()
                .totalActive(habitService.countActive())
                .completedToday(habitService.countCompletedToday())
                .build();

        DashboardDTO.FinanceSummaryShort finance = DashboardDTO.FinanceSummaryShort.builder()
                .monthIncome(financeService.getMonthIncome(month, year))
                .monthExpense(financeService.getMonthExpense(month, year))
                .monthBalance(financeService.getMonthIncome(month, year) - financeService.getMonthExpense(month, year))
                .totalInvested(financeService.getTotalInvested())
                .totalInvestmentValue(financeService.getTotalInvestmentValue())
                .build();

        return DashboardDTO.builder()
                .tasks(tasks)
                .pomodoro(pomodoro)
                .habits(habits)
                .finance(finance)
                .build();
    }
}
