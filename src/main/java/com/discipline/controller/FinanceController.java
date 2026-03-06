package com.discipline.controller;

import com.discipline.dto.*;
import com.discipline.entity.Investment;
import com.discipline.entity.Transaction;
import com.discipline.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finance")
@RequiredArgsConstructor
public class FinanceController {

    private final FinanceService financeService;

    // ---- Transactions ----

    @GetMapping("/transactions")
    public List<Transaction> findTransactions(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return financeService.findTransactions(startDate, endDate);
    }

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(@RequestBody TransactionCreateDTO dto) {
        return financeService.createTransaction(dto);
    }

    @PutMapping("/transactions/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody TransactionUpdateDTO dto) {
        return financeService.updateTransaction(id, dto);
    }

    @DeleteMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable Long id) {
        financeService.deleteTransaction(id);
    }

    // ---- Investments ----

    @GetMapping("/investments")
    public List<Investment> findAllInvestments() {
        return financeService.findAllInvestments();
    }

    @PostMapping("/investments")
    @ResponseStatus(HttpStatus.CREATED)
    public Investment createInvestment(@RequestBody InvestmentCreateDTO dto) {
        return financeService.createInvestment(dto);
    }

    @PutMapping("/investments/{id}")
    public Investment updateInvestment(@PathVariable Long id, @RequestBody InvestmentUpdateDTO dto) {
        return financeService.updateInvestment(id, dto);
    }

    @DeleteMapping("/investments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvestment(@PathVariable Long id) {
        financeService.deleteInvestment(id);
    }

    // ---- Summaries ----

    @GetMapping("/summary")
    public FinanceSummaryDTO getFinanceSummary(
            @RequestParam int month,
            @RequestParam int year) {
        return financeService.getFinanceSummary(month, year);
    }

    @GetMapping("/investments/summary")
    public InvestmentSummaryDTO getInvestmentSummary() {
        return financeService.getInvestmentSummary();
    }
}
