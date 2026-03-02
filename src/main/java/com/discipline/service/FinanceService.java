package com.discipline.service;

import com.discipline.dto.*;
import com.discipline.entity.Investment;
import com.discipline.entity.Transaction;
import com.discipline.repository.InvestmentRepository;
import com.discipline.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceService {

    private final TransactionRepository transactionRepository;
    private final InvestmentRepository investmentRepository;

    // ---- Transactions ----

    public List<Transaction> findTransactions(String startDate, String endDate) {
        return transactionRepository.findByDateBetweenOrderByDateDesc(
                LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    public Transaction createTransaction(TransactionCreateDTO dto) {
        Transaction tx = Transaction.builder()
                .description(dto.getDescription())
                .amount(BigDecimal.valueOf(dto.getAmount()))
                .type(dto.getType())
                .category(dto.getCategory())
                .date(LocalDate.parse(dto.getDate()))
                .build();
        return transactionRepository.save(tx);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    // ---- Investments ----

    public List<Investment> findAllInvestments() {
        return investmentRepository.findAll();
    }

    public Investment createInvestment(InvestmentCreateDTO dto) {
        Investment inv = Investment.builder()
                .name(dto.getName())
                .type(dto.getType())
                .amountInvested(BigDecimal.valueOf(dto.getAmountInvested()))
                .currentValue(BigDecimal.valueOf(dto.getCurrentValue() != null ? dto.getCurrentValue() : dto.getAmountInvested()))
                .purchaseDate(LocalDate.parse(dto.getPurchaseDate()))
                .notes(dto.getNotes())
                .build();
        return investmentRepository.save(inv);
    }

    public void deleteInvestment(Long id) {
        investmentRepository.deleteById(id);
    }

    // ---- Summaries ----

    public FinanceSummaryDTO getFinanceSummary(int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        BigDecimal totalIncome = transactionRepository.sumByTypeAndDateBetween("income", start, end);
        BigDecimal totalExpense = transactionRepository.sumByTypeAndDateBetween("expense", start, end);
        BigDecimal balance = totalIncome.subtract(totalExpense);

        List<Object[]> expensesByCat = transactionRepository.sumByCategoryAndTypeAndDateBetween("expense", start, end);
        List<Object[]> incomeByCat = transactionRepository.sumByCategoryAndTypeAndDateBetween("income", start, end);

        List<FinanceSummaryDTO.CategoryAmount> expenseCategories = expensesByCat.stream()
                .map(row -> FinanceSummaryDTO.CategoryAmount.builder()
                        .category((String) row[0])
                        .amount(((BigDecimal) row[1]).doubleValue())
                        .build())
                .collect(Collectors.toList());

        List<FinanceSummaryDTO.CategoryAmount> incomeCategories = incomeByCat.stream()
                .map(row -> FinanceSummaryDTO.CategoryAmount.builder()
                        .category((String) row[0])
                        .amount(((BigDecimal) row[1]).doubleValue())
                        .build())
                .collect(Collectors.toList());

        return FinanceSummaryDTO.builder()
                .totalIncome(totalIncome.doubleValue())
                .totalExpense(totalExpense.doubleValue())
                .balance(balance.doubleValue())
                .expensesByCategory(expenseCategories)
                .incomeByCategory(incomeCategories)
                .build();
    }

    public InvestmentSummaryDTO getInvestmentSummary() {
        BigDecimal totalInvested = investmentRepository.sumTotalInvested();
        BigDecimal totalCurrentValue = investmentRepository.sumTotalCurrentValue();
        BigDecimal profit = totalCurrentValue.subtract(totalInvested);
        double profitPercentage = totalInvested.compareTo(BigDecimal.ZERO) > 0
                ? profit.divide(totalInvested, 4, RoundingMode.HALF_UP).doubleValue() * 100
                : 0.0;

        List<Object[]> allocationRaw = investmentRepository.sumAllocationByType();
        List<InvestmentSummaryDTO.AllocationItem> allocation = allocationRaw.stream()
                .map(row -> InvestmentSummaryDTO.AllocationItem.builder()
                        .type((String) row[0])
                        .value(((BigDecimal) row[1]).doubleValue())
                        .build())
                .collect(Collectors.toList());

        return InvestmentSummaryDTO.builder()
                .totalInvested(totalInvested.doubleValue())
                .totalCurrentValue(totalCurrentValue.doubleValue())
                .profit(profit.doubleValue())
                .profitPercentage(profitPercentage)
                .allocation(allocation)
                .build();
    }

    public double getMonthIncome(int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return transactionRepository.sumByTypeAndDateBetween("income", start, end).doubleValue();
    }

    public double getMonthExpense(int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return transactionRepository.sumByTypeAndDateBetween("expense", start, end).doubleValue();
    }

    public double getTotalInvested() {
        return investmentRepository.sumTotalInvested().doubleValue();
    }

    public double getTotalInvestmentValue() {
        return investmentRepository.sumTotalCurrentValue().doubleValue();
    }
}
