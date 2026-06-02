package com.expense.tracker.service;

import com.expense.tracker.dto.ExpenseRequest;
import com.expense.tracker.model.Category;
import com.expense.tracker.model.Expense;
import com.expense.tracker.model.Users;
import com.expense.tracker.repo.ExpenseRepo;
import com.expense.tracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepo expenseRepository;
    private final UserRepo userRepository;

    public Expense addExpense(String email, ExpenseRequest request) {

        Users user = userRepository.findByEmail(email).orElseThrow();

        Expense expense = Expense.builder()
                .title(request.getTitle())
                .amount(request.getAmount())
                .category(Category.valueOf(request.getCategory()))
                .date(request.getDate())
                .user(user)
                .build();

        return expenseRepository.save(expense);
    }

    public List<Expense> getExpenses(String email, LocalDate start, LocalDate end) {

        Users user = userRepository.findByEmail(email).orElseThrow();

        if (start != null && end != null) {
            return expenseRepository.findByUserIdAndDateBetween(user.getId(), start, end);
        }

        return expenseRepository.findByUserId(user.getId());
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Expense updateExpense(Long id, ExpenseRequest request) {

        Expense expense = expenseRepository.findById(id).orElseThrow();

//        expense.setTitle("Groceries");
//        expense.setAmount(2500);
//        expense.setCategory("GROCERIES");
//        expense.setDate("2026-06-01");

        return expenseRepository.save(expense);
    }
}