package com.paisaManagement.service;

import com.paisaManagement.model.Expenses;
import com.paisaManagement.request.ExpenseRequest;

import java.util.List;

public interface ExpenseService {
    void expenseAdd(ExpenseRequest expenseRequest);
    List<Expenses> getExpenses(String jwt);
    void expensesEdit(ExpenseRequest expenseRequest);
}
