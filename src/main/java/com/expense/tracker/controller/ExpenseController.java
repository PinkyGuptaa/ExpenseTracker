package com.expense.tracker.controller;

import com.expense.tracker.dto.ExpenseRequest;
import com.expense.tracker.model.Expense;
import com.expense.tracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public Expense addExpense(@RequestBody ExpenseRequest request,
                              Principal principal) {

        return expenseService.addExpense(principal.getName(), request);
    }

    @GetMapping
    public List<Expense> getExpenses(
            @RequestParam(required = false) LocalDate start,
            @RequestParam(required = false) LocalDate end,
            Principal principal) {

        return expenseService.getExpenses(principal.getName(), start, end);
    }

    @PutMapping("/{id}")
    public Expense update(@PathVariable Long id,
                          @RequestBody ExpenseRequest request) {

        return expenseService.updateExpense(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "Deleted";
    }
}
